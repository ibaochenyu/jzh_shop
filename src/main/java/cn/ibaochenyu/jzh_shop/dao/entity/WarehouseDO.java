package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("t_warehouse")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseDO {

    private Long id;
    private Long truthFactoryId;
    private Long truthStylerId;
    private Long stockCount;


}
