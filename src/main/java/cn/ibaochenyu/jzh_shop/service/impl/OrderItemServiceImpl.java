package cn.ibaochenyu.jzh_shop.service.impl;

import cn.ibaochenyu.jzh_shop.dao.entity.OrderItemDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.OrderItemMapper;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTO;
import cn.ibaochenyu.jzh_shop.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemMapper orderItemMapper;


    @Override
    public void save(OrderItemDO aDO) {
        orderItemMapper.insert(aDO);
        return;
    }

    @Override
    public void saveStylerToOrderItem(StylerDTO stylerDTO) {

        //服务请求的工厂id，样式id，个数，扔到OrderItem表
        OrderItemDO tempDO = OrderItemDO.builder()
                .truthStylerId(stylerDTO.getTruthStylerId())
                .truthFactoryId(stylerDTO.getTruthFactoryId())
                .productCount(stylerDTO.getStockCount())
                        .build();
        orderItemMapper.insert(tempDO);



    }
}
