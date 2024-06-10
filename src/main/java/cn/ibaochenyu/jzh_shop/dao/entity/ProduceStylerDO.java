package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_produce_styler")
public class ProduceStylerDO {
    private Long id;


//    private int truthStylerId;

    private int showStylerId;
    private String stylerName;
}
