package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_produce_salary")
public class ProduceSalaryDO {
    private Long id;

    private Date date;
    private int truthStylerId;
    private BigDecimal unitPrice;//java中BigDecimal，mysql用decimal，长度10，小数点2？？？
}
