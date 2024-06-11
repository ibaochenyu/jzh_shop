package cn.ibaochenyu.jzh_shop.controller;

import cn.ibaochenyu.jzh_shop.PageParam;
import cn.ibaochenyu.jzh_shop.ServerResponseEntity;
import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceStylerDO;
import cn.ibaochenyu.jzh_shop.dao.entity.WarehouseDO;
import cn.ibaochenyu.jzh_shop.service.CommodityService;
import cn.ibaochenyu.jzh_shop.service.ProduceService;
import cn.ibaochenyu.jzh_shop.service.WarehouseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wareHouseHandle")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping("saveWarehouse")
    public ServerResponseEntity<Void> saveWarehouse(@RequestBody WarehouseDO aDO){//本来传参无法进来，把其他人的import拿来就好了
        warehouseService.mySave(aDO);
        return ServerResponseEntity.success();
    }


    @PostMapping("saveProduceDOToWarehouse")
    public ServerResponseEntity<Void> saveProduceDOToWarehouse(@RequestBody ProduceDO aDO){//本来传参无法进来，把其他人的import拿来就好了
        warehouseService.saveProduceDOToWarehouse(aDO);
        return ServerResponseEntity.success();
    }

    @PutMapping("saveWarehouseAddCount")
    public ServerResponseEntity<Void> saveWarehouseAddCount(@RequestBody WarehouseDO aDO){//本来传参无法进来，把其他人的import拿来就好了
        warehouseService.mySave(aDO);
        return ServerResponseEntity.success();
    }

    @GetMapping//truthStylerId  commodityStatus
    public ServerResponseEntity<IPage<WarehouseDO>> getPageWarehouse(@RequestParam(name="truthStylerId", required = false) Long truthStylerId, PageParam<WarehouseDO> page){
        IPage<WarehouseDO> rt=warehouseService.getPageWareHouse(truthStylerId,page);
        //List<CommodityDO> tempList;
        //tempList.add(new CommodityDO())
        return ServerResponseEntity.success(rt);
    }


}

//
//{
//        "truthFactoryId":12,
//        "truthStylerId":83009,
//        "stockCount":34
//        }
