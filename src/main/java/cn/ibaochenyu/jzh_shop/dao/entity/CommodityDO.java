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


    //商品状态：
    //1: 生产后入数据库记载，以商城有信息
    //2：锁定：用户下单。可能锁在支付付款
    //3：付款完成并给用户
    private Long commodityStatus;
}
