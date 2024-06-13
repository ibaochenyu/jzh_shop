package cn.ibaochenyu.jzh_shop.service;

import cn.ibaochenyu.jzh_shop.TicketPurchaseRespDTO;
import cn.ibaochenyu.jzh_shop.util.PageParam;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;
import cn.ibaochenyu.jzh_shop.dao.entity.WarehouseDO;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WarehouseService extends IService<WarehouseDO>{
    boolean takeTokenFromBucket(StylerDTO requstStylerDTO);

    void mySave(WarehouseDO aDO);

    IPage<WarehouseDO> getPageWareHouse(Long truthStylerId, PageParam<WarehouseDO> page);

    void saveProduceToWarehouseAndCountPlus(ProduceDO aDO);

    TicketPurchaseRespDTO locateStylerFromWarehouse(StylerDTO stylerDTO);

    TicketPurchaseRespDTO executePurchaseTickets(StylerDTO stylerDTO);
}
