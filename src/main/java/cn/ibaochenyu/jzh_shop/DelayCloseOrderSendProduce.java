package cn.ibaochenyu.jzh_shop;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
//import com.github.xiaoymin.knife4j.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class DelayCloseOrderSendProduce {

    private final RocketMQTemplate rocketMQTemplate;
    private final ConfigurableEnvironment environment;

    public BaseSendExtendDTO buildBaseSendExtendParam(DelayCloseOrderEvent messageSendEvent) {
        return BaseSendExtendDTO.builder()
                .eventName("延迟关闭订单")
                .keys(messageSendEvent.getRtInsertId())
                .topic(OrderRocketMQConstant.ORDER_DELAY_CLOSE_TOPIC_KEY)
                .tag(OrderRocketMQConstant.ORDER_DELAY_CLOSE_TAG_KEY)
                .sentTimeout(2000L)
                // RocketMQ 延迟消息级别 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
                //.delayLevel(14)//10m数过来是第14个
                .delayLevel(3)//10m数过来是第14个
                .build();
    }

    //
    public Message<?> buildMessage(DelayCloseOrderEvent messageSendEvent, BaseSendExtendDTO requestParam) {
        String keys = StrUtil.isEmpty(requestParam.getKeys() ) ? UUID.randomUUID().toString() : requestParam.getKeys();
        MessageWrapper temp=new MessageWrapper();
        temp.setKeys(requestParam.getKeys());
        //temp.setMessage("999");
        temp.setMessage(messageSendEvent);
        String tempStr=temp.toString();
        //messageSendEvent
        return MessageBuilder
                .withPayload(tempStr)
                .setHeader(MessageConst.PROPERTY_KEYS, keys)
                .setHeader(MessageConst.PROPERTY_TAGS, requestParam.getTag())
                .build();
    }//MessageBuilder装载消息，设置头keys，设置头tags。但是为什么要设置这些？？？？

    public SendResult sendMessage(DelayCloseOrderEvent messageSendEvent) {
        BaseSendExtendDTO baseSendExtendDTO = buildBaseSendExtendParam(messageSendEvent);
        SendResult sendResult;
        try {
            StringBuilder destinationBuilder = StrUtil.builder().append(baseSendExtendDTO.getTopic());
            if (StrUtil.isNotBlank(baseSendExtendDTO.getTag())) {
                destinationBuilder.append(":").append(baseSendExtendDTO.getTag());//index12306_order-service_delay-close-order_topic:index12306_order-service_delay-close-order_tag
            }
            sendResult = rocketMQTemplate.syncSend(
                    destinationBuilder.toString(),
                    buildMessage(messageSendEvent, baseSendExtendDTO),
//                    MessageBuilder.withPayload("Hello, RocketMQ!").setHeader("key", "value")
//                            .build(),

                    baseSendExtendDTO.getSentTimeout(),
                    Optional.ofNullable(baseSendExtendDTO.getDelayLevel()).orElse(0)
            );//后来在rokcetMQ-console的可视化看到 这个消息了，在topic：index12306...dalya-close-....
            log.info("[{}] 消息发送结果：{}，消息ID：{}，消息Keys：{}", baseSendExtendDTO.getEventName(), sendResult.getSendStatus(), sendResult.getMsgId(), baseSendExtendDTO.getKeys());//[延迟关闭订单] 消息发送结果：SEND_OK，消息ID：7F000001261063947C6B51AE3C290001，消息Keys：414239674251264568576
        } catch (Throwable ex) {
            log.error("[{}] 消息发送失败，消息体：{}", baseSendExtendDTO.getEventName(), JSON.toJSONString(messageSendEvent), ex);
            throw ex;
        }
        return sendResult;
    }
}
