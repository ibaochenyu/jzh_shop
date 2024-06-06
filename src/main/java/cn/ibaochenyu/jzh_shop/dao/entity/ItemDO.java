package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_item")
public class ItemDO {
    private Long id;


    private int truthItemId;

    private int showItemId;
    private String itemName;
}
