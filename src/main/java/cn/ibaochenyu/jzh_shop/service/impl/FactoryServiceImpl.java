package cn.ibaochenyu.jzh_shop.service.impl;

import cn.ibaochenyu.jzh_shop.dao.entity.FactoryDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.FactoryMapper;
import cn.ibaochenyu.jzh_shop.service.FactoryService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FactoryServiceImpl implements FactoryService {

    private final FactoryMapper factoryMapper;
    @Override
    public void saveFactory(FactoryDO aDo) {
        factoryMapper.insert(aDo);
    }

//    @Override
//    public void getAllFactory() {
//        List<FactoryDO> rtList=factoryMapper.selectList(Wrappers.emptyWrapper());//这些写法没见过，需要记忆一下；
//    }
}
