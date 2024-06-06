package cn.ibaochenyu.jzh_shop;

import cn.ibaochenyu.jzh_shop.dao.entity.BasicDO;
import cn.ibaochenyu.jzh_shop.dto.resp.BasicQueryRespDTO;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

}
