package cn.ibaochenyu.jzh_shop.controller;


import cn.ibaochenyu.jzh_shop.dao.entity.FactoryDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.FactoryMapper;
import cn.ibaochenyu.jzh_shop.myResponse.ServerResponseEntity;
import cn.ibaochenyu.jzh_shop.service.FactoryService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FactoryController {

    //private final FactoryService factoryService;
    private final FactoryMapper factoryMapper;

    @PostMapping("saveFactory")
    public void saveFactory(FactoryDO aDo){
        //factoryService.saveFactory(aDo);
        factoryMapper.insert(aDo);
    }

    @GetMapping("getAllFactory")
    public ServerResponseEntity<List<FactoryDO>> getAllFactory(){
        //我明白了，直接引入factoryMapper就行，否则接口太多了
       // factoryService.getAllFactory();
        List<FactoryDO> rtList=factoryMapper.selectList(Wrappers.emptyWrapper());//这种写法需要记忆一下
        return ServerResponseEntity.success(rtList);
    }

    @GetMapping("getAllFactory2")
    public List<FactoryDO> getAllFactory2(){
        List<FactoryDO> rtList=factoryMapper.selectList(Wrappers.emptyWrapper());//这种写法需要记忆一下
        return rtList;
    }

}
