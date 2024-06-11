package cn.ibaochenyu.jzh_shop.controller;

import cn.ibaochenyu.jzh_shop.ServerResponseEntity;
import cn.ibaochenyu.jzh_shop.dao.entity.OrderMainDO;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTO;
import cn.ibaochenyu.jzh_shop.service.OrderMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OrderMainController {

    private final OrderMainService orderMainService;

    @GetMapping("saveOrderMain")
    public ServerResponseEntity<Void> saveOrderMain(@RequestBody OrderMainDO orderMainDO){
        orderMainService.save(orderMainDO);
        return ServerResponseEntity.success();
    }

    @PostMapping("saveOrderMain")
    public ServerResponseEntity<Void> saveStylerToOrderMain(@RequestBody StylerDTO stylerDTO){
        orderMainService.saveStylerToOrderMain(stylerDTO);
        return ServerResponseEntity.success();
    }





//    @PostMapping("saveStylerToOrderItem")
//    public ServerResponseEntity<Void> saveStylerToOrderItem(@RequestBody OrderMainDO orderMainDO){//本来传参无法进来，把其他人的import拿来就好了
//        orderMainService.saveStylerToOrderItem(orderMainDO);
//        return ServerResponseEntity.success();
//    }




}
