package cn.ibaochenyu.jzh_shop.mq;

import cn.ibaochenyu.jzh_shop.mq.MessageWrapper;
import cn.ibaochenyu.jzh_shop.mq.MyCustomEvent;
import cn.ibaochenyu.jzh_shop.mq.OrderRocketMQConstant;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(
        topic = OrderRocketMQConstant.ORDER_DELAY_CLOSE_TOPIC_KEY,//topic
        selectorExpression = OrderRocketMQConstant.ORDER_DELAY_CLOSE_TAG_KEY,//tag
        consumerGroup = OrderRocketMQConstant.TICKET_DELAY_CLOSE_CG_KEY//consumerGroup
)
public final class DelayCloseOrderConsumer implements RocketMQListener<MessageWrapper<MyCustomEvent>> {


        //防止重复消费的方法：幂等：
        //方法一；mysql主键
        //方法二：redis的key

        ////顺序消费的关键第二部：消费者有序拉
        ///实现MessageListenerOrderly并重写consumeMessage
//       consumer.registerMessageListener(new MessageListenerOrderly() {
//                @Override
//                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext consumeOrderlyContext) {
//                        try {
//                                MessageExt messageExt = msgs.get(0);
//                                String msgBody = new String(messageExt.getBody(),"utf-8");
//                                System.out.println(" 接收新的消息:消息内容为："+msgBody);
//                        } catch (Exception e) {
//                                e.printStackTrace();
//                                System.out.println(e);
//                        }
//                        return ConsumeOrderlyStatus.SUCCESS;
//                }
        //对于铁路，是不用顺序消费的
        //因为令牌大闸，隔绝了所有的无效流量，并且令牌大闸里的令牌数量和列车座位数量是一一对应的，可以保证绝对不会超卖。
        //不管车辆余票的缓存早一点扣减和晚一点扣减，最终结果是一致的就行

        @Override
        public void onMessage(MessageWrapper<MyCustomEvent> delayCloseOrderEventMessageWrapper){
                log.info("[延迟关闭订单] 开始消费：{}",JSON.toJSONString(delayCloseOrderEventMessageWrapper));
                MyCustomEvent myCustomEvent =delayCloseOrderEventMessageWrapper.getMessage();
                String orderSn= myCustomEvent.getOrderMainId();

                int a=2;
                int b=3;

                //防止消息丢失的第三步--消费端：onMessage完毕后，自动调用Acknowledgment.acknowledge()
                //方法二：失败重试
        }
}
