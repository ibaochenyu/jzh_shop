package cn.ibaochenyu.jzh_shop;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(
        topic = OrderRocketMQConstant.ORDER_DELAY_CLOSE_TOPIC_KEY,
        selectorExpression = OrderRocketMQConstant.ORDER_DELAY_CLOSE_TAG_KEY,
        consumerGroup = OrderRocketMQConstant.TICKET_DELAY_CLOSE_CG_KEY
)
public final class DelayCloseOrderConsumer implements RocketMQListener<MessageWrapper<DelayCloseOrderEvent>> {


        @Override
        public void onMessage(MessageWrapper<DelayCloseOrderEvent> delayCloseOrderEventMessageWrapper){
                log.info("[延迟关闭订单] 开始消费：{}",JSON.toJSONString(delayCloseOrderEventMessageWrapper));
                DelayCloseOrderEvent delayCloseOrderEvent=delayCloseOrderEventMessageWrapper.getMessage();
                String orderSn=delayCloseOrderEvent.getOrderMainId();

                int a=2;
                int b=3;
        }
}
