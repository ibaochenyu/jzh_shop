package cn.ibaochenyu.jzh_shop.service.impl;

import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.CommodityMapper;
import cn.ibaochenyu.jzh_shop.service.CommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//别放了@Service
@Service
@RequiredArgsConstructor
//public class CommodityServiceImpl implements CommodityService {

//参考的是：
//orderItemService.saveBatch(orderItemDOList);
//orderPassengerRelationService.saveBatch(orderPassengerRelationDOList);
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, CommodityDO> implements CommodityService {


    //service接下来写mapper层
    private final CommodityMapper commodityMapper;
    public void mySave(CommodityDO aDo){
        commodityMapper.insert(aDo);
    }

    //
    //https://blog.csdn.net/knock_me/article/details/132165909
    //但是，IService的saveBatch()方法实现的批量插入其实是伪批量，其底层实现仍然是一条条数据进行插入的。
//    public void saveListIds(List<Long> ids){
//        commodityMapper
//    }
}
