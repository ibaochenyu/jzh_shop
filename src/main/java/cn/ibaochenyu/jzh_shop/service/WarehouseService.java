package cn.ibaochenyu.jzh_shop.service;

import cn.ibaochenyu.jzh_shop.PageParam;
import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;
import cn.ibaochenyu.jzh_shop.dao.entity.WarehouseDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WarehouseService extends IService<WarehouseDO>{
    void mySave(WarehouseDO aDO);

    IPage<WarehouseDO> getPageWareHouse(Long truthStylerId, PageParam<WarehouseDO> page);

    void saveProduceDOToWarehouse(ProduceDO aDO);
}
