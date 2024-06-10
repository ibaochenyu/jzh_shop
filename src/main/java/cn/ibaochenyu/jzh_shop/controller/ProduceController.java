package cn.ibaochenyu.jzh_shop.controller;

import cn.ibaochenyu.jzh_shop.PageParam;
import cn.ibaochenyu.jzh_shop.ServerResponseEntity;
import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;
import cn.ibaochenyu.jzh_shop.service.CommodityService;
import cn.ibaochenyu.jzh_shop.service.ProduceService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import cn.ibaochenyu.jzh_shop.PageParam;
import cn.ibaochenyu.jzh_shop.Result;
import cn.ibaochenyu.jzh_shop.Results;
import cn.ibaochenyu.jzh_shop.ServerResponseEntity;
import cn.ibaochenyu.jzh_shop.annotation.SysLogMyAnnotation;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;
import cn.ibaochenyu.jzh_shop.dto.resp.BasicQueryRespDTO;
import cn.ibaochenyu.jzh_shop.dto.resp.ProduceQueryRespDTO;
import cn.ibaochenyu.jzh_shop.service.BasicService;
import cn.ibaochenyu.jzh_shop.service.ProduceService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/produceHandle")
public class ProduceController {
    private final ProduceService produceService;

    private final CommodityService  commodityService;


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



    //    @SysLogMyAnnotation(mvalue="请求searchPageResult")
    @RequestMapping("/searchPageResult") //传入在前端的文本要写2020-05-07
//public ServerResponseEntity<List<ProduceQueryRespDTO>> getOneProduceRtResult(@RequestParam(name="produce_date", defaultValue = "2020-05-07") @DateTimeFormat(pattern="yyyy-MM-dd") Date produce_date,
//                                                                       @RequestParam(name="truth_styler_id", defaultValue = "82002") int truth_styler_id,
//                                                                       @RequestParam(name="truth_worker_id", defaultValue = "1") int truth_worker_id) {
//参考：public ServerResponseEntity<IPage<HotSearch>> page(HotSearch hotSearch,PageParam<HotSearch> page){
    public ServerResponseEntity<IPage<ProduceDO>> getOneProduceRtResult(@RequestParam(name="produceDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date produceDate,
                                                                        @RequestParam(name="truthStylerId", required = false) Integer truthStylerId,
                                                                        @RequestParam(name="truthWorkerId", required = false) Integer truthWorkerId,
                                                                        PageParam<ProduceDO> page) {
        IPage<ProduceDO> rt=produceService.getOneProduce(produceDate,truthStylerId,truthWorkerId,page);
        return ServerResponseEntity.success(rt);
    }

    @RequestMapping("/getOneProduceInfo/{id}")
    //参考public ServerResponseEntity<HotSearch> info(@PathVariable("id") Long id){
    public ServerResponseEntity<ProduceDO> getOneProduceInfo(@PathVariable("id") Long id) {
        ProduceDO rt=produceService.getOneProduceInfo(id);
        return ServerResponseEntity.success(rt);
    }

    //    @SysLogMyAnnotation(mvalue="saveProduce")
    @PostMapping//RequestBody???
    public ServerResponseEntity<Void> save(@RequestBody ProduceDO produceDO) {
        //int rt=produceService.save(produceDO);
        //能不能返回一个插入的值呢？？？
        produceService.save(produceDO);
        int produceCount=produceDO.getProduceCount();

        //List<CommodityDO> tempList;
        CommodityDO tempDO=new CommodityDO();
        tempDO.setTruthStylerId(produceDO.getTruthStylerId());
        tempDO.setCommodityStatus(1L);//设置商品要进入数据库
        for(int s=0;s<produceCount;s++) {

            //这么写不行，会出现：; Duplicate entry '1800034706216124417' for key 't_commodity.PRIMARY'] with root cause
            commodityService.save(tempDO);
        }
        //commodityService

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
