package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_produce")
public class ProduceDO {
    private Long id;//不是很明白修改为produceId后，无法UUID自增。用了@tableid也无效
//铁路是自增UUID，mall是自增id //这里先暂时自增id吧

//    @JsonFormat(pattern = "yyyy-MM-dd")
    //private Date produce_date;
    //java是Date类型。对应mysql不是Date，而是DateTime
    private Date produceDate;

    private int truthItemId;

    private int truthWorkerId;
    private int produceCount;


//    private String ctime;



}
