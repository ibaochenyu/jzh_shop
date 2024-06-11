package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_warehouse")
public class WarehouseDO {
    private Long id;
    private Long truth_factory_id;
    private Long truth_styler_id;
    private Long stock_count;

}
