package cn.ibaochenyu.jzh_shop.controller;

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

import java.util.Date;
import java.util.List;

//无法map，可能是文件位置和package的原因

//同根目录后，直接移入controller包没问题



//参考TicketController

//@RestController 是一个组合注解，它结合了 @Controller 和 @ResponseBody 注解的功能
//但是有时候需要将方法的返回值直接作为响应的主体内容，而不是解析为视图。为了实现这个目的，我们可以在方法上使用 @ResponseBody 注解。
//@Controller
@RestController

//RequiredArgsConstructor会将类的每一个final字段或者non-null字段生成一个构造方法
//@RequiredArgsConstructor是Lombok的一个注解，简化了我们对@Autowired书写
//添加上去，否则private final BasicService basicService;报错
@RequiredArgsConstructor
@RequestMapping("/produceHandle")
public class BasicController {

    private final BasicService basicService;

    private final ProduceService produceService;
////http://localhost:8081/getOneProduce?produce_date=2020-05-07&truth_item_id=82002&truth_worker_id=1



//    @RequestMapping("/getOneProduce")
    //public ProduceQueryRespDTO getOneProduce(Date produce_date, int truth_item_id, int truth_worker_id) {
//    public List<ProduceQueryRespDTO> getOneProduce(@RequestParam(name="produce_date", defaultValue = "2020-05-07") @DateTimeFormat(pattern="yyyy-MM-dd") Date produce_date,
//                                             @RequestParam(name="truth_item_id", defaultValue = "82002") int truth_item_id,
//                                             @RequestParam(name="truth_worker_id", defaultValue = "1") int truth_worker_id) {



//    //使用 @RequestParam 注解的方法参数默认为必填参数。
//    public List<ProduceQueryRespDTO> getOneProduce(@RequestParam(name="produce_date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date produce_date,
//                                                   @RequestParam(name="truth_item_id", required = false) int truth_item_id,
//                                                   @RequestParam(name="truth_worker_id", required = false) int truth_worker_id) {
//        //return produceService.getOneProduce(produce_date,truth_item_id,truth_worker_id);
//        return produceService.getOneProduce(produce_date,truth_item_id,truth_worker_id);
//    }



    @SysLogMyAnnotation(mvalue="请求searchPageResult")
@RequestMapping("/searchPageResult") //传入在前端的文本要写2020-05-07
//public ServerResponseEntity<List<ProduceQueryRespDTO>> getOneProduceRtResult(@RequestParam(name="produce_date", defaultValue = "2020-05-07") @DateTimeFormat(pattern="yyyy-MM-dd") Date produce_date,
//                                                                       @RequestParam(name="truth_item_id", defaultValue = "82002") int truth_item_id,
//                                                                       @RequestParam(name="truth_worker_id", defaultValue = "1") int truth_worker_id) {
//参考：public ServerResponseEntity<IPage<HotSearch>> page(HotSearch hotSearch,PageParam<HotSearch> page){
public ServerResponseEntity<IPage<ProduceDO>> getOneProduceRtResult(@RequestParam(name="produceDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date produceDate,
                                                                              @RequestParam(name="truthItemId", required = false) Integer truthItemId,
                                                                              @RequestParam(name="truthWorkerId", required = false) Integer truthWorkerId,
                                                                              PageParam<ProduceDO> page) {
    IPage<ProduceDO> rt=produceService.getOneProduce(produceDate,truthItemId,truthWorkerId,page);
    return ServerResponseEntity.success(rt);
}

    @RequestMapping("/getOneProduceInfo/{id}")
    //参考public ServerResponseEntity<HotSearch> info(@PathVariable("id") Long id){
    public ServerResponseEntity<ProduceDO> getOneProduceInfo(@PathVariable("id") Long id) {
        ProduceDO rt=produceService.getOneProduceInfo(id);
        return ServerResponseEntity.success(rt);
    }

    @SysLogMyAnnotation(mvalue="saveProduce")
    @PostMapping//RequestBody???
    public ServerResponseEntity<Void> save(@RequestBody ProduceDO produceDO) {
        //int rt=produceService.save(produceDO);
        //能不能返回一个插入的值呢？？？
        produceService.save(produceDO);
        return ServerResponseEntity.success();
    }

    @SysLogMyAnnotation(mvalue="updateProduce")
    @PutMapping
    public ServerResponseEntity<Void> update(@RequestBody ProduceDO produceDO) {
        produceService.update(produceDO);
        return ServerResponseEntity.success();
    }

    @SysLogMyAnnotation(mvalue="deleteProduce")
    @DeleteMapping
    public ServerResponseEntity<Void> delete(@RequestBody ProduceDO produceDO) {
        produceService.delete(produceDO);
        return ServerResponseEntity.success();
    }


    ////////

    @RequestMapping("/queryV1")//http://localhost:8080/queryV1?mid=2
    public BasicQueryRespDTO queryV1(@RequestParam(name = "mid", defaultValue = "1") String mid) {
        return basicService.dateQuery(mid);
    }


    @RequestMapping("/hello")
    //@ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    @RequestMapping("/page2")
    public String html() {
        return "page2.html";
    }
}


//@DateTimeFormat(pattern="yyyy-MM-dd")
//y: 年 (Year)
//yyyy: 4位数年份，例如 2023
//yy: 2位数年份，例如 23
//M: 月 (Month)
//MM: 月份，用两位数字表示，例如 01 到 12
//MMM: 月份的缩写，例如 Jan, Feb
//MMMM: 月份的全称，例如 January, February
//d: 日 (Day)
//dd: 一个月中的第几天，用两位数字表示，例如 01 到 31
//d: 一个月中的第几天，用一位或两位数字表示，例如 1 到 31
//H: 小时 (Hour in 24-hour format)
//HH: 24小时制的小时，用两位数字表示，例如 00 到 23
//h: 小时 (Hour in 12-hour format)
//[hh: 12小时制的小时]，用两位数字表示，例如 01 到 12
//
//yyyy-MM-dd: 2023-06-07
//HH:mm:ss: 14:30:45
//@RequestParam(name="timestamp") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//例如 2023-06-07 14:30:45。

//MM (大写) 表示月份。 month
//mm (小写) 表示分钟。 minute
//HH (大写) 表示24小时制的小时。
//hh (小写) 表示12小时制的小时。
