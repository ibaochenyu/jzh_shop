package cn.ibaochenyu.jzh_shop.service.impl;

import cn.ibaochenyu.jzh_shop.dao.entity.OrderItemDO;
import cn.ibaochenyu.jzh_shop.dao.entity.OrderMainDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.OrderMainMapper;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTO;
import cn.ibaochenyu.jzh_shop.service.OrderMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderMainServiceImpl implements OrderMainService {
    private final OrderMainMapper orderMainMapper;
    @Override
    public void save(OrderMainDO orderMainDO) {
        orderMainMapper.insert(orderMainDO);
        return;
    }

    @Override
    public void saveStylerToOrderMain(StylerDTO stylerDTO) {

        //表的状态：1：生产出来并进入商城，2：订单锁定状态  3：订单支付完成
        //服务请求的工厂id，样式id，个数，扔到OrderItem表
        OrderMainDO tempDO = OrderMainDO.builder()
                .truthUserId(1145146L)
                .orderStatus(1)
                .build();
        orderMainMapper.insert(tempDO);


    }


}
