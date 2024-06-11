package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("t_order_main")
public class OrderMainDO {
    private Long id;
    private Date createDate;
    private Date payDate;
    private Long trueUserId;
    private int orderStatus;
}
