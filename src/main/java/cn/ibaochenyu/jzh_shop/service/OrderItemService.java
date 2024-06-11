package cn.ibaochenyu.jzh_shop.service;

import cn.ibaochenyu.jzh_shop.dao.entity.OrderItemDO;
import cn.ibaochenyu.jzh_shop.dao.entity.OrderMainDO;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTO;

public interface OrderItemService {
    void save(OrderItemDO aDO);

    void saveStylerToOrderItem(StylerDTO aDO);
}
