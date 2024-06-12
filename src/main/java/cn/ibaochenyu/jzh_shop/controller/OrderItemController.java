package cn.ibaochenyu.jzh_shop.controller;

import cn.ibaochenyu.jzh_shop.myResponse.ServerResponseEntity;
import cn.ibaochenyu.jzh_shop.dao.entity.OrderItemDO;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTO;
import cn.ibaochenyu.jzh_shop.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;
    @GetMapping("saveOrderItem")
    public ServerResponseEntity<Void> saveOrderItem(@RequestBody OrderItemDO aDO){
        orderItemService.save(aDO);
        return ServerResponseEntity.success();

    }

    @GetMapping("saveStylerToOrderItem")
    public ServerResponseEntity<Void> saveStylerToOrderItem(@RequestBody StylerDTO aDO){
        orderItemService.saveStylerToOrderItem(aDO);
        return ServerResponseEntity.success();

    }

}
