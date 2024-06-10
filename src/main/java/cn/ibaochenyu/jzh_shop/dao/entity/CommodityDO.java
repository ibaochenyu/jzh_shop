package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("t_commodity")
//下面三个参考铁路写法，写了才能使用.builder()
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommodityDO {
    private Long id;
    private Date produceDate;
    private Long truthFactoryId;
    private Long truthStylerId;

    private Long truthWorkerId;


    //商品状态：
    //1: 生产后入数据库记载，以商城有信息
    //2：锁定：用户下单。可能锁在支付付款
    //3：付款完成并给用户
    private int commodityStatus;
}
