package cn.ibaochenyu.jzh_shop.controller;

import cn.ibaochenyu.jzh_shop.ServerResponseEntity;
import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import cn.ibaochenyu.jzh_shop.service.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//别忘了加@RestController，respsonbody+controller //这样controller和service都需要注解，对应起来了
@RequiredArgsConstructor
@RequestMapping("/commodityHandle")
public class CommodityController {
    private final CommodityService commodityService;

    //@PostMapping("test")    //{"truthStylerId":12,"commodityStatus":13}
    @PostMapping//truthStylerId  commodityStatus
    public ServerResponseEntity<Void> saveCommodity(@RequestBody CommodityDO aDo){
        commodityService.save(aDo);
        //List<CommodityDO> tempList;
        //tempList.add(new CommodityDO())
        return ServerResponseEntity.success();
    }

//    @PostMapping//truthStylerId  commodityStatus
//    public ServerResponseEntity<Void> saveListCommodityID(@RequestBody List<Long> ids){
//        commodityService.save(aDo);
//        return ServerResponseEntity.success();
//    }
}
