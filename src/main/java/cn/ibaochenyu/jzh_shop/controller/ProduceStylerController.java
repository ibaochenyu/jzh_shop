package cn.ibaochenyu.jzh_shop.controller;

import cn.ibaochenyu.jzh_shop.ServerResponseEntity;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceStylerDO;
import cn.ibaochenyu.jzh_shop.service.ProduceStylerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor//有点忘了
public class ProduceStylerController {

    private final ProduceStylerService produceStylerService;

//    @PostMapping("purchase")
//    public ServerResponseEntity<Void> purchaseStylerAndGiveOneCommodity(@RequestBody ProduceStylerDO psDO){
//
//        produceStylerService
//
//
//    }





}
