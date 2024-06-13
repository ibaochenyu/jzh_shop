//package cn.ibaochenyu.jzh_shop.mq;
//
//import cn.ibaochenyu.jzh_shop.mq.MessageWrapper;
//import cn.ibaochenyu.jzh_shop.mq.MyCustomEvent;
//import cn.ibaochenyu.jzh_shop.mq.OrderRocketMQConstant;
//import com.alibaba.fastjson.JSON;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//@RocketMQMessageListener(
//        topic = OrderRocketMQConstant.ORDER_DELAY_CLOSE_TOPIC_KEY,//topic
//        selectorExpression = OrderRocketMQConstant.ORDER_DELAY_CLOSE_TAG_KEY,//tag
//        consumerGroup = OrderRocketMQConstant.TICKET_DELAY_CLOSE_CG_KEY//consumerGroup
//)
//public final class DelayCloseOrderConsumer implements RocketMQListener<MessageWrapper<MyCustomEvent>> {
//
//
//        @Override
//        public void onMessage(MessageWrapper<MyCustomEvent> delayCloseOrderEventMessageWrapper){
//                log.info("[延迟关闭订单] 开始消费：{}",JSON.toJSONString(delayCloseOrderEventMessageWrapper));
//                MyCustomEvent myCustomEvent =delayCloseOrderEventMessageWrapper.getMessage();
//                String orderSn= myCustomEvent.getOrderMainId();
//
//                int a=2;
//                int b=3;
//        }
//}
