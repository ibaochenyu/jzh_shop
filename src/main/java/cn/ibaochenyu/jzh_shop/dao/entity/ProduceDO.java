package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_produce")
public class ProduceDO {
    //https://blog.csdn.net/qq_41619796/article/details/125411221 //https://blog.csdn.net/zqm1992409670/article/details/125900250
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;//不是很明白修改为produceId后，无法UUID自增。用了@tableid也无效
//铁路是自增UUID，mall是自增id //这里先暂时自增id吧

//    @JsonFormat(pattern = "yyyy-MM-dd")
    //private Date produce_date;
    //java是Date类型。对应mysql不是Date，而是DateTime
    private Date produceDate;
//一旦修改了。需要全局替换。1.这里  2.truth_Item_id   3.truthStylerId
    private Long truthStylerId;

    private Long truthWorkerId;
    private int produceCount;


//    private String ctime;



}
