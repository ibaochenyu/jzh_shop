package cn.ibaochenyu.jzh_shop.controller;

import cn.ibaochenyu.jzh_shop.dto.resp.BasicQueryReqDTO;
import cn.ibaochenyu.jzh_shop.dto.resp.BasicQueryRespDTO;
import cn.ibaochenyu.jzh_shop.service.BasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping("/queryV1")
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
