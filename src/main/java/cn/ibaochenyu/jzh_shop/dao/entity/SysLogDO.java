package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;
@Data
//这种写法不行：@Table(t_sys_log)。得有个双引号
@TableName("t_sys_log")
public class SysLogDO {//问题：需要implements Serializable 吗？？？

    private Long id;
    //时间，地点（注释），人物，做了什么（类和方法），做了什么参数，耗时多久
    //mysql里头设置id不是long类型，而应该是bigint类型
    private Date date;

    private String comment;

    private String ip;
    private String operatorClass;
    private String operatorArgv;


    //时间跨度是long么？？？
    private Long duration;



}
