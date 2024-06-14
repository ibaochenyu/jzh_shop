package cn.ibaochenyu.jzh_shop.mq;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.ibaochenyu.jzh_shop.CanalBinlogEvent;
import cn.ibaochenyu.jzh_shop.StringRedisTemplateProxy;
import cn.ibaochenyu.jzh_shop.dao.entity.WarehouseDO;
import cn.ibaochenyu.jzh_shop.mq.MessageWrapper;
import cn.ibaochenyu.jzh_shop.mq.MyCustomEvent;
import cn.ibaochenyu.jzh_shop.mq.OrderRocketMQConstant;
import cn.ibaochenyu.jzh_shop.toolkit.BeanUtil;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

import static cn.ibaochenyu.jzh_shop.RedisKeyConstant.WAREHOUSE_INFO_FACTORYID;


@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(///看到message详情没有topic显示
        topic = OrderRocketMQConstant.CANAL_COMMON_SYNC_TOPIC_KEY,
//        selectorExpression = OrderRocketMQConstant.ORDER_DELAY_CLOSE_TAG_KEY,
        consumerGroup = OrderRocketMQConstant.CANAL_COMMON_SYNC_CG_KEY //我估摸这个consumer group写啥，控制台就显示啥了
)
//public final class CanalCommonSyncBinlogConsumer implements RocketMQListener<CanalBinlogEvent> {
    public final class CanalCommonSyncBinlogConsumer implements RocketMQListener<CanalBinlogEvent> {


    @Value("${ticket.availability.cache-update.type:}")
    private String ticketAvailabilityCacheUpdateType;

    private final StringRedisTemplateProxy distributedCache;

    //由于取消订单时候
    @Override//我找不到.sendMessage(canalBinlogEvent)，可能是canal信息直接发送给他了
    public void onMessage(CanalBinlogEvent message) {//我找不到这个CanalBinlogEvent是怎么构建出参数的？？？？

        //要求是非修改表，求老消息存在，且是update类型，且设置内配了binglog
        if (message.getIsDdl()
                || CollUtil.isEmpty(message.getOld())
                || !Objects.equals("UPDATE", message.getType())
                || !StrUtil.equals(ticketAvailabilityCacheUpdateType, "binlog")) {
            return;
        }//数据定义语言（DDL）数据定义语言是由SQL语言集中负责数据结构定义与数据库对象定义的语言，并且由CREATE、ALTER、DROP和TRUNCATE四个语法组成。比如：


        int a=3;
        int b=4;
        String tabler=message.getTable();

        //string怎么能==，这种不行
        //一旦是"xxx"和new处理的，即使数据相同，也会返回false啊

        //https://blog.csdn.net/nxj_climb/article/details/113175127

        if(tabler.equals("t_warehouse")){
        //if(tabler=="t_warehouse"){
            execute(message);
        }


    }

    public void execute(CanalBinlogEvent message) {
//        List<Map<String, Object>> messageDataList = message.getData().stream()
//                .filter(each -> each.get("status") != null)
//                .filter(each -> Objects.equals(each.get("status"), "30"))//CLOSED(30, "已取消"),
//                .toList();//查找成员status是30，如果一个都不符合，直接返回

//message.getOld()
//        List<Map<String, Object>> messageDataList = message.getOld().stream()
//        .filter(each -> each.get("stock_count") != null)
//        //.filter(each -> Objects.equals(each.get("status"), "30"))//CLOSED(30, "已取消"),
//        .toList();//查找成员status是30，如果一个都不符合，直接返回
//
//
//        for(int s=0;s<messageDataList.size();s++){
//            Map<String,Object> mso=messageDataList.get(0);
//            if (oldDataMap.get("seat_status") != null && StrUtil.isNotBlank(oldDataMap.get("seat_status").toString())) {
//
//        }

//改变字段（string）-》改变的值（object）
        List<Map<String, Object>> messageDataList = new ArrayList<>();
        List<Map<String, Object>> actualOldDataList = new ArrayList<>();
        for (int i = 0; i < message.getOld().size(); i++) {//讨论所有改变的旧值
            Map<String, Object> oldDataMap = message.getOld().get(i);//甚至可以((LinkedHashMap) message.getOld().get(0)).size讨论一下改变老属性的属性个数
            if (oldDataMap.get("stock_count") != null && StrUtil.isNotBlank(oldDataMap.get("stock_count").toString())) {
                Map<String, Object> currentDataMap = message.getData().get(i);
                if (true) {
                    actualOldDataList.add(oldDataMap);//StrUtil.equalsAny输入了三个参数啊 //actual a.实际的，现行的
                    messageDataList.add(currentDataMap);
                }
            }
        }

        if (CollUtil.isEmpty(messageDataList) || CollUtil.isEmpty(actualOldDataList)) {
            return;
        }

        StringRedisTemplate stringRedisTemplate= distributedCache.getInstance();
        Map<String, Map<Long, Integer>> cacheChangeKeyMap = new HashMap<>();
        for (int i = 0; i < messageDataList.size(); i++) {
            Map<String, Object> each = messageDataList.get(i);
            Map<String, Object> actualOldData = actualOldDataList.get(i);
            Integer oldCount = Integer.valueOf((String) actualOldData.get("stock_count")) ;
            Integer newCount = Integer.valueOf((String) each.get("stock_count"));
            String factoryId = each.get("truth_factory_id").toString();
            //String转long    Long.valueOf()
            String stylerId = (each.get("truth_styler_id").toString());
            String Ider = (each.get("id").toString());
            String hashCacheKey = WAREHOUSE_INFO_FACTORYID +factoryId;
            log.info("将{}数量转为{}",oldCount,newCount);
            //stringRedisTemplate.opsForHash().increment(hashCacheKey, stylerId, newCount);
            WarehouseDO aDo=new WarehouseDO( Long.valueOf(Ider),Long.valueOf(factoryId),Long.valueOf(stylerId),newCount.intValue());
            //stringRedisTemplate.opsForHash().put(hashCacheKey, stylerId, JSON.toJSONString(aDo));
            long newCountint=1L;
            stringRedisTemplate.opsForHash().increment(hashCacheKey, stylerId,  newCountint);
        }//redisTemplate.opsForHash().increment，对应redis的hincrby





//        if (CollUtil.isEmpty(messageDataList)) {
//            return;
//        }
        int a=2;
        int b=3;

        return;
        //StringRedisTemplate instance = (StringRedisTemplate) distributedCache.getInstance();
//        String iwantKey=WAREHOUSE_INFO_FACTORYID+
//        instance.opsForHash().increment(cacheKey, String.valueOf(seatType), num)));

//        for (Map<String, Object> each : messageDataList) {
//            Result<TicketOrderDetailRespDTO> orderDetailResult = ticketOrderRemoteService.queryTicketOrderByOrderSn(each.get("order_sn").toString());
//            TicketOrderDetailRespDTO orderDetailResultData = orderDetailResult.getData();
//            if (orderDetailResult.isSuccess() && orderDetailResultData != null) {
//                String trainId = String.valueOf(orderDetailResultData.getTrainId());
//                List<TicketOrderPassengerDetailRespDTO> passengerDetails = orderDetailResultData.getPassengerDetails();
//                seatService.unlock(trainId, orderDetailResultData.getDeparture(), orderDetailResultData.getArrival(), BeanUtil.convert(passengerDetails, TrainPurchaseTicketRespDTO.class));////mysql中，把状态变为可售
//                ticketAvailabilityTokenBucket.rollbackInBucket(orderDetailResultData);//令牌的redis更新
//            }
//        }
    }
}

//CONSUMED_BUT_FILTERED

