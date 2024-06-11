package cn.ibaochenyu.jzh_shop.service.impl;

import cn.ibaochenyu.jzh_shop.dao.entity.OrderMainDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.OrderMainMapper;
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




}
