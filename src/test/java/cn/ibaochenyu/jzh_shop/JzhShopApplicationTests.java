package cn.ibaochenyu.jzh_shop;

import cn.ibaochenyu.jzh_shop.dao.entity.BasicDO;
import cn.ibaochenyu.jzh_shop.dto.resp.BasicQueryRespDTO;
import cn.ibaochenyu.jzh_shop.testStrategy.DiscountStrategy;
import cn.ibaochenyu.jzh_shop.testStrategy.DiscountStrategyFactory;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;
import java.util.Optional;
import com.github.dozermapper.core.Mapper;


@SpringBootTest
class JzhShopApplicationTests {

    protected static Mapper BEAN_MAPPER_BUILDER;//import com.github.dozermapper.core.Mapper;

    static {
        BEAN_MAPPER_BUILDER = DozerBeanMapperBuilder.buildDefault();
    }
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



    @Test
    void 断言_测试(){
        int result=1+2;
        Assert.assertEquals(3,result);

    }

////////////
//    https://blog.csdn.net/winterking3/article/details/123913150
    @Test
    void testAssertThrows() {
        Assertions.assertThrows(ArithmeticException.class, () -> errorMethod());
    }

    private void errorMethod() {
        int a = 1/0;
    }

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

    @Test
    void 测试策略工厂() {
        DiscountStrategy discountStrategy= DiscountStrategyFactory.chooseStrategy("1");
        Double discount=discountStrategy.discount(10D);
        System.out.println("优惠后金额："+discount);//9.5
    }
}
