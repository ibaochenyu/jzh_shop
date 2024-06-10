package cn.ibaochenyu.jzh_shop.service;

import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import com.baomidou.mybatisplus.extension.service.IService;

//public interface CommodityService {
public interface CommodityService extends IService<CommodityDO> {


    void mySave(CommodityDO aDo);

//    void saveListIds(List<Long> ids);
}
