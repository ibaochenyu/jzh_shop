package cn.ibaochenyu.jzh_shop;

public class OrderRocketMQConstant {

    /**
     * 订单服务相关业务 Topic Key
     */
    public static final String ORDER_DELAY_CLOSE_TOPIC_KEY = "jzh_order-service_delay-close-order_topic";

    /**
     * 购票服务创建订单后延时关闭业务 Tag Key
     */
    public static final String ORDER_DELAY_CLOSE_TAG_KEY = "jzh_order-service_delay-close-order_tag";

    public static final String TICKET_DELAY_CLOSE_CG_KEY = "jzh_ticket-service_delay-close-order_cg";

//    /**
//     * 订单服务相关业务 Topic Key
//     */
//    public static final String ORDER_DELAY_CLOSE_TOPIC_KEY = "index12306_order-service_delay-close-order_topic${unique-name:}";
//
//    /**
//     * 购票服务创建订单后延时关闭业务 Tag Key
//     */
//    public static final String ORDER_DELAY_CLOSE_TAG_KEY = "index12306_order-service_delay-close-order_tag${unique-name:}";
//
//    /**
//     * 支付服务相关业务 Topic Key
//     */
//    public static final String PAY_GLOBAL_TOPIC_KEY = "index12306_pay-service_topic${unique-name:}";
//
//    /**
//     * 支付结果回调状态 Tag Key
//     */
//    public static final String PAY_RESULT_CALLBACK_TAG_KEY = "index12306_pay-service_pay-result-callback_tag${unique-name:}";
//
//    /**
//     * 支付结果回调订单消费者组 Key
//     */
//    public static final String PAY_RESULT_CALLBACK_ORDER_CG_KEY = "index12306_pay-service_pay-result-callback-order_cg${unique-name:}";
//
//    /**
//     * 退款结果回调订单 Tag Key
//     */
//    public static final String REFUND_RESULT_CALLBACK_TAG_KEY = "index12306_pay-service_refund-result-callback_tag${unique-name:}";
//
//    /**
//     * 退款结果回调订单消费者组 Key
//     */
//    public static final String REFUND_RESULT_CALLBACK_ORDER_CG_KEY = "index12306_pay-service_refund-result-callback-order_cg${unique-name:}";
//
//
//
//    /**
//     * 购票服务创建订单后延时关闭业务消费者组 Key
//     */
//    public static final String TICKET_DELAY_CLOSE_CG_KEY = "index12306_ticket-service_delay-close-order_cg${unique-name:}";
//
//    /**
//     * Canal 监听数据库余票变更 Topic Key
//     *///这里懂了。修改seat表的某数据status成员，会发送这个topic
//    public static final String CANAL_COMMON_SYNC_TOPIC_KEY = "index12306_canal_ticket-service_common-sync_topic${unique-name:}";
//
//    /**
//     * Canal 监听数据库余票变更业务消费者组 Key
//     */
//    public static final String CANAL_COMMON_SYNC_CG_KEY = "index12306_canal_ticket-service_common-sync_cg${unique-name:}";
//
//
//    /**
//     * 支付结果回调购票消费者组 Key
//     */
//    public static final String PAY_RESULT_CALLBACK_TICKET_CG_KEY = "index12306_pay-service_pay-result-callback-ticket_cg${unique-name:}";
}
