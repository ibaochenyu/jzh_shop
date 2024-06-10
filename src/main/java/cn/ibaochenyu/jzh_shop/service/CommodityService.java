package cn.ibaochenyu.jzh_shop.service;

import cn.ibaochenyu.jzh_shop.PageParam;
import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceStylerDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

//public interface CommodityService {
public interface CommodityService extends IService<CommodityDO> {


    void mySave(CommodityDO aDo);

    //返回商品页：每一个商品都有展示
    IPage<CommodityDO> getPageCommodity(Long truthStylerId, PageParam<CommodityDO> page);


    //初步设定
    //筛选：种类，工厂id，
    //是哪个工人生产的不重要

    //返回商品页：某个商品对应和它的个数
    List<CommodityDO> getPageCommodityAndCnt(Long truthStylerId,List<Integer> factoryIds);

    int statusChangesToLock(CommodityDO aDo);

    int purchaseStylerAndGiveOneCommodity(ProduceStylerDO aDo);

//    void saveListIds(List<Long> ids);
}
