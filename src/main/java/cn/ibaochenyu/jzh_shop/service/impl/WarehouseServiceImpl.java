package cn.ibaochenyu.jzh_shop.service.impl;

import cn.ibaochenyu.jzh_shop.PageParam;
import cn.ibaochenyu.jzh_shop.dao.entity.*;
import cn.ibaochenyu.jzh_shop.dao.mapper.WarehouseMapper;
import cn.ibaochenyu.jzh_shop.service.WarehouseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import cn.ibaochenyu.jzh_shop.CommodityStatusEnum;
import cn.ibaochenyu.jzh_shop.PageParam;
import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.CommodityMapper;
import cn.ibaochenyu.jzh_shop.service.CommodityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor//Required(需要->必须的)-Args-Constructor
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, WarehouseDO> implements WarehouseService {
    private final WarehouseMapper warehouseMapper;
    @Override
    public void mySave(WarehouseDO aDO) {
        warehouseMapper.insert(aDO);
    }

    @Override
    public IPage<WarehouseDO> getPageWareHouse(Long truthStylerId, PageParam<WarehouseDO> page) {
        LambdaQueryWrapper<WarehouseDO> queryWrapper = Wrappers.lambdaQuery(WarehouseDO.class);

        if (truthStylerId != null) {
            queryWrapper.eq(WarehouseDO::getTruthStylerId, truthStylerId);
        }


        Page<WarehouseDO> pageTemp=page;
        IPage<WarehouseDO> ipage=warehouseMapper.selectPage(pageTemp,queryWrapper);//返回ipage

        return ipage;
    }

    @Override//http://127.0.0.1:8081/wareHouseHandle/saveProduceDOToWarehouse
    public void saveProduceDOToWarehouse(ProduceDO produceDO) {//{"truthFactoryId":12,"truthStylerId":23,"produceCount":111}

        Long truthFactoryId = produceDO.getTruthFactoryId();
        Long truthStylerId = produceDO.getTruthStylerId();
        int produceCount = produceDO.getProduceCount();

        // 构建查询条件
        //QueryWrapper<WarehouseDO> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<WarehouseDO> queryWrapper = Wrappers.lambdaQuery(WarehouseDO.class);

        queryWrapper.eq(WarehouseDO::getTruthFactoryId, truthFactoryId)
                .eq(WarehouseDO::getTruthStylerId, truthStylerId);

        // 查找WarehouseDO记录
        WarehouseDO warehouseDO = warehouseMapper.selectOne(queryWrapper);

        if (warehouseDO != null) {
            // 存在记录，更新stockCount
            warehouseDO.setStockCount(warehouseDO.getStockCount() + produceCount);
            warehouseMapper.updateById(warehouseDO);
        } else {
            // 不存在记录，插入新行
            warehouseDO = new WarehouseDO();
            warehouseDO.setTruthFactoryId(truthFactoryId);
            warehouseDO.setTruthStylerId(truthStylerId);
            warehouseDO.setStockCount(produceCount);
            warehouseMapper.insert(warehouseDO);
        }

    }

}
