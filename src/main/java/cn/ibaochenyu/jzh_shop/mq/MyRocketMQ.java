//package cn.ibaochenyu.jzh_shop.controller;
//
//import cn.ibaochenyu.jzh_shop.mq.MyCustomEvent;
//import cn.ibaochenyu.jzh_shop.mq.MessageWrapper;
//import cn.ibaochenyu.jzh_shop.mq.OrderRocketMQConstant;
//import com.alibaba.fastjson.JSON;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//
//import java.util.Optional;
//import java.util.UUID;
//import cn.hutool.core.util.StrUtil;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class MyRocketMQ {
//
//    private final RocketMQTemplate rocketMQTemplate;
//
//
//    /**
//     * 消息事件通用发送
//     *
//     * @param m_Event 消息发送事件
//     * @return 消息发送返回结果
//     */
//    public SendResult sendMessage(MyCustomEvent m_Event) {//DelayCloseOrderEvent是一种消息种类
//        //原来参考buildBaseSendExtendParam的作用
//        String localKey=(m_Event.getOrderMainId());
//        Long localTimeout=10000L;
//        Integer localDelayLevel=3;
//        String localTag=OrderRocketMQConstant.ORDER_DELAY_CLOSE_TAG_KEY;
//        String localTopic=OrderRocketMQConstant.ORDER_DELAY_CLOSE_TOPIC_KEY;
//        //////
//        String localEvent="延迟关闭订单";
//        //这个东西基本没用了
////        BaseSendExtendDTO baseSendExtendDTO = BaseSendExtendDTO.builder()//最最主要的：keyu，topic，tag。不重要的：timeout、延迟等级
////                .eventName(localEvent)
////                .keys(localKey)
////                .topic(localTopic)
////                .tag(localTag)
////                .sentTimeout(localTimeout)
////                //.sentTimeout(2000L)
////                // RocketMQ 延迟消息级别 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
////                //.delayLevel(14)//10m数过来是第14个
////                .delayLevel(localDelayLevel)//10m数过来是第14个
////                .build();
//        //////////
//
//        SendResult sendResult;
//        try {
//            //topic
//            StringBuilder destinationBuilder = StrUtil.builder().append(localTopic);
//            if (StrUtil.isNotBlank(localTag) ){//topic+tag
//                destinationBuilder.append(":").append(localTag);//index12306_order-service_delay-close-order_topic:index12306_order-service_delay-close-order_tag
//            }
//            String destinationStr=destinationBuilder.toString();
//
//            //////
//
//            String possibleChangekey = StrUtil.isEmpty(localKey ) ? UUID.randomUUID().toString() : localKey;
//            Message<?> tempMB= MessageBuilder//import cn.hutool.core.util.StrUtil;
//                    .withPayload(new MessageWrapper(localKey, m_Event))//key,message,uuid,时间
//                    //.withPayload(new MessageWrapper(requestParam.getKeys(), messageSendEvent).toString())
////                    .setHeader(MessageConst.PROPERTY_KEYS, possibleChangekey)
////                    .setHeader(MessageConst.PROPERTY_TAGS, localTag)
//                    .setHeader("KEYS", possibleChangekey)
//                    .setHeader("TAGS", localTag)
//                    .build();
//            //////
//
//            sendResult = rocketMQTemplate.syncSend(
//                    destinationStr,//？？？
//                    tempMB,
//                    localTimeout,
//                    Optional.ofNullable(localDelayLevel).orElse(0)
//            );//后来在rokcetMQ-console的可视化看到 这个消息了，在topic：index12306...dalya-close-....
//            log.info("[{}] 消息发送结果：{}，消息ID：{}，消息Keys：{}",localEvent, sendResult.getSendStatus(), sendResult.getMsgId(), localKey);//[延迟关闭订单] 消息发送结果：SEND_OK，消息ID：7F000001261063947C6B51AE3C290001，消息Keys：414239674251264568576
//        } catch (Throwable ex) {
//            log.error("[{}] 消息发送失败，消息体：{}", localEvent, JSON.toJSONString(m_Event), ex);
//            throw ex;
//        }
//        return sendResult;
//    }
//}
//
////rocketMQTemplate.syncSend(
////    目标地址 //topic+tags的string
////    消息体   //Payload的key，Payload的自定义类，header的keys，header的tags
////    timeout
////    延迟等级
////)
////
////消息体=MessageBuilder
////.withPayload(localKey, m_Event,uuid,时间)
////.setHeader("KEYS", possibleChangekey)
////.setHeader("TAGS", localTag)
////.build();
//
////主要topic,tag,key,自定义类，timeout，延迟等级
//
//
////发送时候是依照topic和tag来rocketMQTemplate.syncSend
////接收时候是依照topic和tag和conumer来onmessage
////
////message：
////    搜索topic：可查找tag、key下对应的messageID
////    搜索key：
////    搜索messageID
////    Consumer:
////    就是以前我没用springboot3注入时候，发现SubscriptionGroup没有jzh身影
////    Topic：
////    发现topic自动生成，但是没有对应的consumer
