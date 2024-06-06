package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//DAO（Data Access Object）.Dao的作用是封装对数据库的访问：增删改查，不涉及业务逻辑，只是达到按某个条件获得指定数据的要求。
//Entity层，顾名思义就是实体层，放置一个个实体，及其相应的set、get方法。如果想要对数据库进行一些操作（比如说读取）的话，就要先写entity层。

//@Data //@Data 注解的主要作用是提高代码的简洁，使用这个注解可以省去代码中大量的get()、 set()、 toString()等方法；


//sql->
// BasicDO->
//BasicMapper extends BaseMapper<BasicDO>->     (BasicMapper.selectOne)
//public interface BasicService extends IService<BasicDO>

import java.util.Date;

@Data
@TableName("t_basic")
public class BasicDO {
    private Long id;//java中用Long，mysql中用bigint

    private int workId;
    private String name;
    private int sex;

    //如果此处string错打字为int
    //com.mysql.cj.exceptions.DataConversionException: Cannot determine value type from string '黄小鑫的家'
    private String homeAddress;

    //https://www.cnblogs.com/zendwang/p/mybatis-jdbctype-date.html
//    1） DATETIME
//    显示格式：yyyy-MM-dd HH:mm:ss
//    时间范围:[ '1000-01-01 00:00:00'到'9999-12-31 23:59:59']
//
//            2） DATE
//    显示格式：yyyy-MM-dd
//    时间范围：['1000-01-01'到'9999-12-31']
//
//            3） TIMESTAMP
//    显示格式：yyyy-MM-dd HH:mm:ss
//    时间范围:[ '1970-01-01 00:00:00'到'2037-12-31 23:59:59']
//
//            （JAVA没有DateTime这个类，Date类能够同时表示日期和时间），java.util.Date实际上是能够表示MySQL的三种字段类型：
//            3.1 date
//3.2 datetime
//3.3 timestamp

    ////所以我这里mysql里头用的是datetime类型，java用的是Date
    private Date date;

}
