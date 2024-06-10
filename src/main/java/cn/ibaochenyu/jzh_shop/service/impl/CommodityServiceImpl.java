package cn.ibaochenyu.jzh_shop.service.impl;

import cn.ibaochenyu.jzh_shop.CommodityStatusEnum;
import cn.ibaochenyu.jzh_shop.PageParam;
import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.CommodityMapper;
import cn.ibaochenyu.jzh_shop.service.CommodityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


//别放了@Service
@Service
@RequiredArgsConstructor
//public class CommodityServiceImpl implements CommodityService {

//参考的是：
//orderItemService.saveBatch(orderItemDOList);
//orderPassengerRelationService.saveBatch(orderPassengerRelationDOList);
//ServiceImpl容易忘
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, CommodityDO> implements CommodityService {


    //service接下来写mapper层
    private final CommodityMapper commodityMapper;
    public void mySave(CommodityDO aDo){
        commodityMapper.insert(aDo);
    }


    @Override//返回商品页：每一个商品都有展示
    public IPage<CommodityDO> getPageCommodity(Long truthStylerId, PageParam<CommodityDO> page)
    {

        LambdaQueryWrapper<CommodityDO> queryWrapper = Wrappers.lambdaQuery(CommodityDO.class);

        if (truthStylerId != null) {
            queryWrapper.eq(CommodityDO::getTruthStylerId, truthStylerId);
        }


        Page<CommodityDO> pageTemp=page;
        IPage<CommodityDO> ipage=commodityMapper.selectPage(pageTemp,queryWrapper);//返回ipage

        return ipage;
    }

    @Override//返回商品页：某个商品对应和它的个数
    public List<CommodityDO> getPageCommodityWithCnt(Long truthStylerId, List<Integer> factoryId)
    {

//        LambdaQueryWrapper<CommodityDO> queryWrapper = Wrappers.lambdaQuery(CommodityDO.class);
//
//        if (truthStylerId != null) {
//            queryWrapper.eq(CommodityDO::getTruthStylerId, truthStylerId);
//        }


//        Page<CommodityDO> pageTemp=page;
        //List<CommodityDO> ipage=commodityMapper.getPageCommodityWithCnt(pageTemp,queryWrapper);//返回ipage
        List<CommodityDO> lister=commodityMapper.getPageCommodityWithCnt(truthStylerId,factoryId);
//        IPage<CommodityDO> ipage=Ipage<lister>;
//此处缺乏List<CommodityDO>到IPage<CommodityDO>的转化
        return lister;
    }



    @Override//商品状态改变。当可能用户下单时，商品的状态会改变
    public int statusChangesToLock(CommodityDO aDo) {//
        //这种和数据库底层的逻辑要写到ServiceImpl

        //找到id一样的CommodityDO
        LambdaUpdateWrapper<CommodityDO> updateWrapper = Wrappers.lambdaUpdate(CommodityDO.class)
                .eq(CommodityDO::getId, aDo.getId())
                .eq(CommodityDO::getCommodityStatus, CommodityStatusEnum.AVAILABLE.getCode());

        //需要判定原来的锁是状态0吗？？为什么铁路没有判定？？

        //变更状态值为locked
        CommodityDO updateSeatDO = CommodityDO.builder()
                .commodityStatus(CommodityStatusEnum.LOCKED.getCode())//设置改变值
                .build();


        int rt=commodityMapper.update(updateSeatDO, updateWrapper);
        return rt;
    }

    //
    //https://blog.csdn.net/knock_me/article/details/132165909
    //但是，IService的saveBatch()方法实现的批量插入其实是伪批量，其底层实现仍然是一条条数据进行插入的。
//    public void saveListIds(List<Long> ids){
//        commodityMapper
//    }
}
