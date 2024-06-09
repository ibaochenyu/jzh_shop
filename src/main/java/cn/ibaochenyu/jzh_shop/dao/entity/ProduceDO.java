package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_produce")
public class ProduceDO {
    //@TableId//由于我之前主键是id，后来修改为produceID后，: Field ‘id‘ doesn‘t have a default value
    //可能是mybatis默认是id并设置UUID自增
    //所以手动指明一下好了
    //https://blog.csdn.net/xgysimida/article/details/108200269
    //https://blog.csdn.net/weixin_45797022/article/details/121042485

    //@TableId(value = "produce_id2")//当spring和数据库的字段不一样，手动标识下

    //这里设置type = IdType.AUTO没啥用，还是要在navicat设置自动递增。而且递增时候是老uuid+1了

    @TableId//TableId啥都不写，就是默认UUID吧。//如果主键不是id，那么需要手动标识@TableId//@TableId注解是专门用在主键上的注解
    private Long produceId;////表结构修改了,需要重启spring
//java写produceId，mysql是produceId2,则报错：### Cause: java.sql.SQLSyntaxErrorException: Unknown column 'produce_id' in 'field list'


//    @JsonFormat(pattern = "yyyy-MM-dd")
    //private Date produce_date;
    //java是Date类型。对应mysql不是Date，而是DateTime
    private Date produceDate;

    private int truthItemId;

    private int truthWorkerId;
    private int produceCount;


//    private String ctime;



}
