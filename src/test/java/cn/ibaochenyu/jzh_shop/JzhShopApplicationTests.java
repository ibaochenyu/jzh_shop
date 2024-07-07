package cn.ibaochenyu.jzh_shop;

import cn.ibaochenyu.jzh_shop.dao.entity.BasicDO;
import cn.ibaochenyu.jzh_shop.dto.resp.BasicQueryRespDTO;
import cn.ibaochenyu.jzh_shop.testStrategy.DiscountStrategy;
import cn.ibaochenyu.jzh_shop.testStrategy.DiscountStrategyFactory;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import com.github.dozermapper.core.Mapper;

import javax.sql.DataSource;


@SpringBootTest
class JzhShopApplicationTests {

    protected static Mapper BEAN_MAPPER_BUILDER;//import com.github.dozermapper.core.Mapper;

    static {
        BEAN_MAPPER_BUILDER = DozerBeanMapperBuilder.buildDefault();
    }

    //用途：测试如何使用dozer进行对象转换
    @Test
    void 利用dozer进行对象转换() {
        BasicDO source = new BasicDO();
        Class<cn.ibaochenyu.jzh_shop.dto.resp.BasicQueryRespDTO> clazz = BasicQueryRespDTO.class;
        //source.setId(1);
        source.setId(1L);
        source.setWorkId(3);
        source.setName("黄小鑫");
        source.setSex(1);
        source.setHomeAddress("黄小鑫的家");
        BasicQueryRespDTO temp=Optional.ofNullable(source)
                .map(each -> BEAN_MAPPER_BUILDER.map(each, clazz))
                .orElse(null);
        int a=2;
    }



    //用途：测试断言的使用
    @Test
    void 断言_测试(){
        int result=1+2;
        Assert.assertEquals(3,result);

    }

////////////
    //用途：测试扔出Exception
    //https://blog.csdn.net/winterking3/article/details/123913150
    @Test
    void testAssertThrows() {
        Assertions.assertThrows(ArithmeticException.class, () -> errorMethod());
    }

    private void errorMethod() {
        int a = 1/0;
    }


    //用途：测试一个类的实例化的五种方法
    @SneakyThrows
    @Test
    void 实例化五种方法() {
        //new最常见的，省略

        //第二种
        BasicDO do2 = BasicDO.class.newInstance();
        do2.setHomeAddress("123");

        //第三种
        Constructor<BasicDO> constructor3=BasicDO.class.getConstructor();
        BasicDO do3=constructor3.newInstance();
        do3.setHomeAddress("123");

        //第四种Clone
        //实现Cloneable并重写clone()


        //第五种
        //反序列化
    }
//////

    //用途：测试具体策略工厂设计模式如何使用
    @Autowired
    private DiscountStrategyFactory discountStrategyFactory;//
    @Test
    void 测试策略工厂() {//有点bug。会出现：java.lang.NullPointerException: Cannot invoke "cn.ibaochenyu.jzh_shop.testStrategy.DiscountStrategy.discount(java.lang.Double)" because "discountStrategy" is null
        DiscountStrategy discountStrategy= DiscountStrategyFactory.chooseStrategy("1");
        Double discount=discountStrategy.discount(10D);
        System.out.println("优惠后金额："+discount);//9.5
    }



    //用途：测试常规jdbc如何使用
    @Test
    void 测试jdbc() throws SQLException {//可能扔出SQLException
        //关键字：DataSource
        //Connection      ---sql--->
        //PreparedStatement ,executeQuery,executeUpdate
        //ResultSet

        DataSource dataSource=new MysqlDataSource();
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/jzh_shop?characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&serverTimezone=GMT%2B8");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("root");

        Connection connection=dataSource.getConnection();
        String sql="select * from t_basic";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        ResultSet resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            String name=resultSet.getString("name");
            Integer sex=resultSet.getInt("sex");
            System.out.println("name="+name+";age="+String.valueOf(sex) );
        }

//        name=黄小鑫;age=1
//        name=严小玲26;age=1
//        name=null;age=0
//        name=null;age=0
    }
}
