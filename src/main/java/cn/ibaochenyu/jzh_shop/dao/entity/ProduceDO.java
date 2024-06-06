package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_produce")
public class ProduceDO {
    private Long id;


//    @JsonFormat(pattern = "yyyy-MM-dd")
    //private Date produce_date;
    private Date produceDate;

    private int truthItemId;

    private int truthWorkerId;
    private int produceCount;


//    private String ctime;



}
