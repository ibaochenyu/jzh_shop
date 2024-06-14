package cn.ibaochenyu.jzh_shop.mq;

public class OrderRocketMQConstant {

    /**
     * 订单服务相关业务 Topic Key
     */
    public static final String ORDER_DELAY_CLOSE_TOPIC_KEY = "jzh1_delay-close-order_topic";

    /**
     * 购票服务创建订单后延时关闭业务 Tag Key
     */
    public static final String ORDER_DELAY_CLOSE_TAG_KEY = "jzh1_delay-close-order_tag";

    public static final String TICKET_DELAY_CLOSE_CG_KEY = "jzh1_delay-close-order_cg";


    public static final String CANAL_COMMON_SYNC_TOPIC_KEY = "jzh_index12306_canal_ticket-service_common-sync_topic";

    /**
     * Canal 监听数据库余票变更业务消费者组 Key
     */
    public static final String CANAL_COMMON_SYNC_CG_KEY = "jzh_index12306_canal_ticket-service_common-sync_cg";


}
