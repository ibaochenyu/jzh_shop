package cn.ibaochenyu.jzh_shop.service.impl;

import org.apache.rocketmq.client.producer.SendResult;
import cn.ibaochenyu.jzh_shop.DelayCloseOrderEvent;
import cn.ibaochenyu.jzh_shop.DelayCloseOrderSendProduce;
import cn.ibaochenyu.jzh_shop.dao.entity.OrderItemDO;
import cn.ibaochenyu.jzh_shop.dao.entity.OrderMainDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.OrderMainMapper;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTO;
import cn.ibaochenyu.jzh_shop.service.OrderMainService;
import com.alibaba.fastjson.JSON;
import com.google.protobuf.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderMainServiceImpl implements OrderMainService {
    private final OrderMainMapper orderMainMapper;
    private final DelayCloseOrderSendProduce delayCloseOrderSendProduce;
    @Override
    public void save(OrderMainDO orderMainDO) {
        orderMainMapper.insert(orderMainDO);
        return;
    }

    @Override
    public void saveStylerToOrderMain(StylerDTO stylerDTO) {//

        //表的状态：1：生产出来并进入商城，2：订单锁定状态  3：订单支付完成
        //服务请求的工厂id，样式id，个数，扔到OrderItem表
        OrderMainDO tempDO = OrderMainDO.builder()
                .truthUserId(1145146L)//模拟想要货的用户的id
                .orderStatus(2)//模拟下完单，没有支付的状态
                .build();
        orderMainMapper.insert(tempDO);
        String rtInsertId=String.valueOf(tempDO.getId());//返回插入的行到String

        try {
            // 发送 RocketMQ 延时消息，指定时间后取消订单
            DelayCloseOrderEvent delayCloseOrderEvent = DelayCloseOrderEvent.builder()
                    .rtInsertId(rtInsertId)
                    .rtInsertId2("aaa")
                    .build();
            // 创建订单并支付后延时关闭订单消息怎么办？详情查看：https://nageoffer.com/12306/question
            SendResult sendResult = delayCloseOrderSendProduce.sendMessage(delayCloseOrderEvent);
            if (!Objects.equals(sendResult.getSendStatus(), SendStatus.SEND_OK)) {
                throw new ServiceException("投递延迟关闭订单消息队列失败");
            }
        } catch (Throwable ex) {
            log.error("延迟关闭订单消息队列发送错误，请求参数：{}", JSON.toJSONString(stylerDTO), ex);
//            throw ex;
        }




    }


}
