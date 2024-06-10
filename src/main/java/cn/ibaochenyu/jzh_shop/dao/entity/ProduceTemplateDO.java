package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_produce_template")
public class ProduceTemplateDO {
    private Long id;


//    private int truthTemplateId;

    private int showTemplateId;
    private String templateName;
}
