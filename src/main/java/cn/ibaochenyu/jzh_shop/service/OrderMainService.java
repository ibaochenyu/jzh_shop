package cn.ibaochenyu.jzh_shop.service;

import cn.ibaochenyu.jzh_shop.dao.entity.OrderMainDO;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTO;

public interface OrderMainService {
    void save(OrderMainDO orderMainDO);


    void saveStylerToOrderMain(StylerDTO stylerDTO);
}
