package cn.ibaochenyu.jzh_shop.controller;

import cn.ibaochenyu.jzh_shop.Result;
import cn.ibaochenyu.jzh_shop.Results;
import cn.ibaochenyu.jzh_shop.ServerResponseEntity;
import cn.ibaochenyu.jzh_shop.dto.resp.BasicQueryRespDTO;
import cn.ibaochenyu.jzh_shop.dto.resp.ProduceQueryRespDTO;
import cn.ibaochenyu.jzh_shop.service.BasicService;
import cn.ibaochenyu.jzh_shop.service.ProduceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class BasicController {

    private final BasicService basicService;

    private final ProduceService produceService;

    @RequestMapping("/getOneProduce")
    //public ProduceQueryRespDTO getOneProduce(Date produce_date, int truth_item_id, int truth_worker_id) {
    public List<ProduceQueryRespDTO> getOneProduce(@RequestParam(name="produce_date", defaultValue = "2020-05-07") @DateTimeFormat(pattern="yyyy-MM-dd") Date produce_date,
                                             @RequestParam(name="truth_item_id", defaultValue = "82002") int truth_item_id,
                                             @RequestParam(name="truth_worker_id", defaultValue = "1") int truth_worker_id) {
        //return produceService.getOneProduce(produce_date,truth_item_id,truth_worker_id);
        return produceService.getOneProduce(produce_date,truth_item_id,truth_worker_id);
    }

//    @RequestMapping("/getOneProduceRtResult")
//    public Result<ProduceQueryRespDTO> getOneProduceRtResult(@RequestParam(name="produce_date", defaultValue = "2020-05-07") @DateTimeFormat(pattern="yyyy-MM-dd") Date produce_date,
//                                                     @RequestParam(name="truth_item_id", defaultValue = "82002") int truth_item_id,
//                                                     @RequestParam(name="truth_worker_id", defaultValue = "1") int truth_worker_id) {
//        ProduceQueryRespDTO rt=produceService.getOneProduce(produce_date,truth_item_id,truth_worker_id);
//        return Results.success(rt);
//    }
//    @RequestMapping("/getOneProduceRtResult")
//    public ServerResponseEntity<ProduceQueryRespDTO> getOneProduceRtResult(@RequestParam(name="produce_date", defaultValue = "2020-05-07") @DateTimeFormat(pattern="yyyy-MM-dd") Date produce_date,
//                                                                           @RequestParam(name="truth_item_id", defaultValue = "82002") int truth_item_id,
//                                                                           @RequestParam(name="truth_worker_id", defaultValue = "1") int truth_worker_id) {
//        ProduceQueryRespDTO rt=produceService.getOneProduce(produce_date,truth_item_id,truth_worker_id);
//        return ServerResponseEntity.success(rt);
//    }
@RequestMapping("/getOneProduceRtResult")
public ServerResponseEntity<List<ProduceQueryRespDTO>> getOneProduceRtResult(@RequestParam(name="produce_date", defaultValue = "2020-05-07") @DateTimeFormat(pattern="yyyy-MM-dd") Date produce_date,
                                                                       @RequestParam(name="truth_item_id", defaultValue = "82002") int truth_item_id,
                                                                       @RequestParam(name="truth_worker_id", defaultValue = "1") int truth_worker_id) {
    List<ProduceQueryRespDTO> rt=produceService.getOneProduce(produce_date,truth_item_id,truth_worker_id);
    return ServerResponseEntity.success(rt);
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
