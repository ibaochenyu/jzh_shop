package cn.ibaochenyu.jzh_shop.service.impl;

import cn.ibaochenyu.jzh_shop.PageParam;
import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import cn.ibaochenyu.jzh_shop.dao.entity.WarehouseDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.WarehouseMapper;
import cn.ibaochenyu.jzh_shop.service.WarehouseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import cn.ibaochenyu.jzh_shop.CommodityStatusEnum;
import cn.ibaochenyu.jzh_shop.PageParam;
import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceStylerDO;
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

}
