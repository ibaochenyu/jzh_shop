package cn.ibaochenyu.jzh_shop.service.impl;

import cn.ibaochenyu.jzh_shop.mq.MyCustomEvent;
import cn.ibaochenyu.jzh_shop.webGlobal.JZHcustomException;
import cn.ibaochenyu.jzh_shop.controller.MyRocketMQ;
import cn.ibaochenyu.jzh_shop.dao.entity.OrderMainDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.OrderMainMapper;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTO;
import cn.ibaochenyu.jzh_shop.service.OrderMainService;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderMainServiceImpl implements OrderMainService {
    private final OrderMainMapper orderMainMapper;
    private final MyRocketMQ myRocketMQ;
    @Override
    public void save(OrderMainDO orderMainDO) {
        orderMainMapper.insert(orderMainDO);
        return;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveStylerToOrderMain(StylerDTO stylerDTO) {

        //表的状态：1：生产出来并进入商城，2：订单锁定状态  3：订单支付完成
        //服务请求的工厂id，样式id，个数，扔到OrderItem表
        OrderMainDO tempDO = OrderMainDO.builder()
                .truthUserId(1145146L)
                .orderStatus(1)
                .build();
        orderMainMapper.insert(tempDO);
        log.info("插入的id可能是"+tempDO.getId());
        //参考OrderDO orderDO = OrderDO.builder().orderSn(orderSn)
        //他们是用基金法，我先这里返回insert后的结果吧

        log.info("请求参数：{}", JSON.toJSONString(stylerDTO));
        try {
            // 发送 RocketMQ 延时消息，指定时间后取消订单
            MyCustomEvent myCustomEvent = MyCustomEvent.builder()
                    .orderMainId(String.valueOf( tempDO.getId()))
                    .build();
            // 创建订单并支付后延时关闭订单消息怎么办？详情查看：https://nageoffer.com/12306/question
            SendResult sendResult = myRocketMQ.sendMessage(myCustomEvent);
            if (!Objects.equals(sendResult.getSendStatus(), SendStatus.SEND_OK)) {
                throw new JZHcustomException("投递延迟关闭订单消息队列失败");
            }
        } catch (Throwable ex) {
            log.error("延迟关闭订单消息队列发送错误，请求参数：{}", JSON.toJSONString(stylerDTO), ex);
            throw ex;
        }//JSON.toJSONString(stylerDTO)可以直接返回错误的参数吗？？




    }


}
