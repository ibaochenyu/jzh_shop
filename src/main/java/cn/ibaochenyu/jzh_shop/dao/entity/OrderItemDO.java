package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("t_order_item")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDO {
    private Long id;
    private Long truthOrderMainId;
    private Long truthFactoryId;

    private Long truthStylerId;

    private int productCount;


}
