package cn.ibaochenyu.jzh_shop.controller;

import cn.ibaochenyu.jzh_shop.PageParam;
import cn.ibaochenyu.jzh_shop.ServerResponseEntity;
import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceStylerDO;
import cn.ibaochenyu.jzh_shop.service.CommodityService;
import cn.ibaochenyu.jzh_shop.service.ProduceService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController//别忘了加@RestController，respsonbody+controller //这样controller和service都需要注解，对应起来了
@RequiredArgsConstructor
@RequestMapping("/commodityHandle")
public class CommodityController {
    private final CommodityService commodityService;

    //@PostMapping("test")    //{"truthStylerId":12,"commodityStatus":13}
    @PostMapping//truthStylerId  commodityStatus
    public ServerResponseEntity<Void> saveCommodity(@RequestBody CommodityDO aDo){
        commodityService.mySave(aDo);
        //List<CommodityDO> tempList;
        //tempList.add(new CommodityDO())
        return ServerResponseEntity.success();
    }

    //参考：public void lockSeat(String trainId, String departure, String arrival, List<TrainPurchaseTicketRespDTO> trainPurchaseTicketRespList) {
    @PutMapping//修改Commodity的状态
    public ServerResponseEntity<Integer> changeCommodityStatusToLock(@RequestBody CommodityDO aDo){
        int rtChange=commodityService.statusChangesToLock(aDo);
        //Integer temp=new Integer(rtChange); //'Integer(int)' is deprecated and marked for removal
        Integer temp=Integer.valueOf(rtChange);
        return ServerResponseEntity.success(temp);//此处填充data区域的值是修改的行数
    }








//    @PostMapping//truthStylerId  commodityStatus
//    public ServerResponseEntity<Void> saveListCommodityID(@RequestBody List<Long> ids){
//        commodityService.save(aDo);
//        return ServerResponseEntity.success();
//    }

//http://127.0.0.1:8081/commodityHandle?truthStylerId=83009&current=1&size=5
    @GetMapping//truthStylerId  commodityStatus
    public ServerResponseEntity<IPage<CommodityDO>> getPageCommodity(@RequestParam(name="truthStylerId", required = false) Long truthStylerId, PageParam<CommodityDO> page){
        IPage<CommodityDO> rt=commodityService.getPageCommodity(truthStylerId,page);
        //List<CommodityDO> tempList;
        //tempList.add(new CommodityDO())
        return ServerResponseEntity.success(rt);
    }
}
