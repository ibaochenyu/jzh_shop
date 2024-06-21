package cn.ibaochenyu.jzh_shop.controller;

import cn.ibaochenyu.jzh_shop.annotation.SysLogMyAnnotation;
import cn.ibaochenyu.jzh_shop.webGlobal.JZHcustomException;
import cn.ibaochenyu.jzh_shop.util.PageParam;
import cn.ibaochenyu.jzh_shop.myResponse.ResponseEnum;
import cn.ibaochenyu.jzh_shop.myResponse.ServerResponseEntity;
import cn.ibaochenyu.jzh_shop.dao.entity.BasicDO;
import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;
import cn.ibaochenyu.jzh_shop.dao.entity.WarehouseDO;
import cn.ibaochenyu.jzh_shop.service.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;


@RestController
@RequiredArgsConstructor
@RequestMapping("/produceHandle")
public class ProduceController {
    private final ProduceService produceService;

    private final CommodityService  commodityService;

    private final WarehouseService warehouseService;

    private final BasicService basicService;


////http://localhost:8081/getOneProduce?produce_date=2020-05-07&truth_styler_id=82002&truth_worker_id=1



//    @RequestMapping("/getOneProduce")
    //public ProduceQueryRespDTO getOneProduce(Date produce_date, int truth_styler_id, int truth_worker_id) {
//    public List<ProduceQueryRespDTO> getOneProduce(@RequestParam(name="produce_date", defaultValue = "2020-05-07") @DateTimeFormat(pattern="yyyy-MM-dd") Date produce_date,
//                                             @RequestParam(name="truth_styler_id", defaultValue = "82002") int truth_styler_id,
//                                             @RequestParam(name="truth_worker_id", defaultValue = "1") int truth_worker_id) {



//    //使用 @RequestParam 注解的方法参数默认为必填参数。
//    public List<ProduceQueryRespDTO> getOneProduce(@RequestParam(name="produce_date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date produce_date,
//                                                   @RequestParam(name="truth_styler_id", required = false) int truth_styler_id,
//                                                   @RequestParam(name="truth_worker_id", required = false) int truth_worker_id) {
//        //return produceService.getOneProduce(produce_date,truth_styler_id,truth_worker_id);
//        return produceService.getOneProduce(produce_date,truth_styler_id,truth_worker_id);
//    }



    @SysLogMyAnnotation(mvalue="请求searchPageResult")
    @RequestMapping("/searchPageResult") //传入在前端的文本要写2020-05-07
//public ServerResponseEntity<List<ProduceQueryRespDTO>> getOneProduceRtResult(@RequestParam(name="produce_date", defaultValue = "2020-05-07") @DateTimeFormat(pattern="yyyy-MM-dd") Date produce_date,
//                                                                       @RequestParam(name="truth_styler_id", defaultValue = "82002") int truth_styler_id,
//                                                                       @RequestParam(name="truth_worker_id", defaultValue = "1") int truth_worker_id) {
//参考：public ServerResponseEntity<IPage<HotSearch>> page(HotSearch hotSearch,PageParam<HotSearch> page){
    public ServerResponseEntity<IPage<ProduceDO>> getOneProduceRtResult(@RequestParam(name="produceDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date produceDate,
                                                                        @RequestParam(name="truthStylerId", required = false) Long truthStylerId,
                                                                        @RequestParam(name="truthWorkerId", required = false) Long truthWorkerId,
                                                                        PageParam<ProduceDO> page) {
        IPage<ProduceDO> rt=produceService.getOneProduce(produceDate,truthStylerId,truthWorkerId,page);
        return ServerResponseEntity.success(rt);
    }

    @RequestMapping("/getOneProduceInfo/{id}")
    //参考public ServerResponseEntity<HotSearch> info(@PathVariable("id") Long id){
    public ServerResponseEntity<ProduceDO> getOneProduceInfo(@PathVariable("id") Long id) {

        ProduceDO rt=produceService.getOneProduceInfo(id);
        if(true){
            //throw new JZHcustomException("测试未知错误");//"code": "A00001",//    "msg": "测试未知错误",
            throw new JZHcustomException(ResponseEnum.TEST_EXCEPTION,"好，错误出现了");////{"code": "EXC114514", "msg": "好，错误出现了",
        }
        return ServerResponseEntity.success(rt);
    }

    //    @SysLogMyAnnotation(mvalue="saveProduce")
    @PutMapping("saveProduceDO")//RequestBody???
    @Transactional(rollbackFor = Exception.class)//果然加了Transactional,函数内一起回滚了
    public ServerResponseEntity<Void> saveProduceDO(@RequestBody ProduceDO produceDO) {
        //int rt=produceService.save(produceDO);
        //能不能返回一个插入的值呢？？？


        produceService.save(produceDO);//生产一个produce筐


        int produceCount=produceDO.getProduceCount();
        List<CommodityDO> tempList = new ArrayList<>();
        List<BasicDO> tempListBasic = new ArrayList<>();
        List<WarehouseDO> tempListWarehouse = new ArrayList<>();


//        for(int s=0;s<produceCount;s++) {
//            CommodityDO tempADO=CommodityDO.builder()
//                    .truthStylerId(produceDO.getTruthStylerId())
//                    .commodityStatus(CommodityStatusEnum.AVAILABLE.getCode())//设置状态为可以销售
//                    .build();
//            tempList.add(tempADO);
//            //////
//
//            ////////
//            BasicDO tempADOBaisc=BasicDO.builder()
//                    .workId(s+30)
//                    .name("测试SaveBatch")
//                    .build();
//            tempListBasic.add(tempADOBaisc);
//        }//需要设置commodityService表是无符号、序号自动递增。从调试的记录来看，是批量插入


//        WarehouseDO tempWareADO = WarehouseDO.builder()
//                .truthStylerId(produceDO.getTruthStylerId())
//                .truthFactoryId(produceDO.getTruthFactoryId())
//                .stockCount(produceDO.getProduceCount())//将produce生产的数目给库存
//                .build();

        //不要直接save，可能会触发unique索引
        // warehouseService.save(tempWareADO);
        warehouseService.saveProduceToWarehouseAndCountPlus(produceDO);

//        commodityService.saveBatch(tempList);//produce导致商品详情改变
//        basicService.saveBatch(tempListBasic);;

        return ServerResponseEntity.success();
    }

    //    @SysLogMyAnnotation(mvalue="updateProduce")
    @PutMapping
    public ServerResponseEntity<Void> update(@RequestBody ProduceDO produceDO) {
        produceService.update(produceDO);
        return ServerResponseEntity.success();
    }

    //    @SysLogMyAnnotation(mvalue="deleteProduce")
    //@DeleteMapping("/testDelete")
    @PutMapping("/DeleteListIds")//神坑：为什么一@DeleteMapping+@RequestBody就开始变慢了？如果这里写deleteMapping，前端不会慢；一写@delete，前端就变慢
    public ServerResponseEntity<Void> delete(@RequestBody List<Long> ids) {//这个问题和前端有关。因为postman是正常发送的
//    public ServerResponseEntity<Void> delete2(@RequestBody Long ids) {
//        List<Long> temp= Arrays.asList(ids);

        produceService.delete(ids);
        //produceService.deleteOneId(ids);
        return ServerResponseEntity.success();
    }//不知道为什么delete很久后才来？？？

    @DeleteMapping("/{id}")
    //public ServerResponseEntity<Void> delete(@RequestBody List<Long> ids) {
    public ServerResponseEntity<Void> delete(@PathVariable Long id) {
//        List<Long> temp= Arrays.asList(ids);
        produceService.deleteOneId(id);
        return ServerResponseEntity.success();
    }




}
