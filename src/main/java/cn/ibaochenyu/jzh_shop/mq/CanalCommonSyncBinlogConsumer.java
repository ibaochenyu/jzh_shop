package cn.ibaochenyu.jzh_shop.mq;

import cn.hutool.core.collection.CollUtil;
import cn.ibaochenyu.jzh_shop.CanalBinlogEvent;
import cn.ibaochenyu.jzh_shop.mq.MessageWrapper;
import cn.ibaochenyu.jzh_shop.mq.MyCustomEvent;
import cn.ibaochenyu.jzh_shop.mq.OrderRocketMQConstant;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(//奇怪，为什么接受不到？？？？
        topic = OrderRocketMQConstant.CANAL_COMMON_SYNC_TOPIC_KEY,
        selectorExpression = OrderRocketMQConstant.ORDER_DELAY_CLOSE_TAG_KEY,
        consumerGroup = OrderRocketMQConstant.CANAL_COMMON_SYNC_CG_KEY //我估摸这个consumer group写啥，控制台就显示啥了
)
//public final class CanalCommonSyncBinlogConsumer implements RocketMQListener<CanalBinlogEvent> {
    public final class CanalCommonSyncBinlogConsumer implements RocketMQListener<MessageWrapper<CanalBinlogEvent>> {



    //由于取消订单时候
    @Override//我找不到.sendMessage(canalBinlogEvent)，可能是canal信息直接发送给他了
    public void onMessage(MessageWrapper<CanalBinlogEvent> message) {//我找不到这个CanalBinlogEvent是怎么构建出参数的？？？？
        // 余票 Binlog 更新延迟问题如何解决？详情查看：https://nageoffer.com/12306/question
//        if (message.getIsDdl()
//                || CollUtil.isEmpty(message.getOld())
//                || !Objects.equals("UPDATE", message.getType())
//                || !StrUtil.equals(ticketAvailabilityCacheUpdateType, "binlog")) {
//            return;
//        }//数据定义语言（DDL）数据定义语言是由SQL语言集中负责数据结构定义与数据库对象定义的语言，并且由CREATE、ALTER、DROP和TRUNCATE四个语法组成。比如：
//

        int a=3;
        int b=4;
        //execute(message);

    }
//
//    public void execute(CanalBinlogEvent message) {
//        List<Map<String, Object>> messageDataList = message.getData().stream()
//                .filter(each -> each.get("status") != null)
//                .filter(each -> Objects.equals(each.get("status"), "30"))//CLOSED(30, "已取消"),
//                .toList();//查找成员status是30，如果一个都不符合，直接返回
//        if (CollUtil.isEmpty(messageDataList)) {
//            return;
//        }
//        int a=2;
//        int b=3;
////        for (Map<String, Object> each : messageDataList) {
////            Result<TicketOrderDetailRespDTO> orderDetailResult = ticketOrderRemoteService.queryTicketOrderByOrderSn(each.get("order_sn").toString());
////            TicketOrderDetailRespDTO orderDetailResultData = orderDetailResult.getData();
////            if (orderDetailResult.isSuccess() && orderDetailResultData != null) {
////                String trainId = String.valueOf(orderDetailResultData.getTrainId());
////                List<TicketOrderPassengerDetailRespDTO> passengerDetails = orderDetailResultData.getPassengerDetails();
////                seatService.unlock(trainId, orderDetailResultData.getDeparture(), orderDetailResultData.getArrival(), BeanUtil.convert(passengerDetails, TrainPurchaseTicketRespDTO.class));////mysql中，把状态变为可售
////                ticketAvailabilityTokenBucket.rollbackInBucket(orderDetailResultData);//令牌的redis更新
////            }
////        }
//    }
}

