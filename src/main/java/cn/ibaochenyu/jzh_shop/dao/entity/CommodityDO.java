package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@TableName("t_commodity")
public class CommodityDO {
    private Long id;
    private Long truthStylerId;
    private Long commodityStatus;
}
