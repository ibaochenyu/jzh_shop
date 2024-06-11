package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_order_item")
public class OrderItemDO {
    private Long id;
    private Long trueOrderMainId;
    private Long trueFactoryId;

    private Long trueStylerId;

    private int productCount;
}
