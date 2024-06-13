package cn.ibaochenyu.jzh_shop.service.impl;

import cn.ibaochenyu.jzh_shop.util.PageParam;
import cn.ibaochenyu.jzh_shop.dao.entity.*;
import cn.ibaochenyu.jzh_shop.dao.mapper.BasicMapper;
import cn.ibaochenyu.jzh_shop.dao.mapper.WarehouseMapper;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTO;
import cn.ibaochenyu.jzh_shop.service.WarehouseService;
import cn.ibaochenyu.jzh_shop.webGlobal.JZHcustomException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor//Required(需要->必须的)-Args-Constructor
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, WarehouseDO> implements WarehouseService {
    private final WarehouseMapper warehouseMapper;
    private final BasicMapper basicMapper;
    @Override
    public void mySave(WarehouseDO aDO) {
        warehouseMapper.insert(aDO);
    }

    @Override
    public IPage<WarehouseDO> getPageWareHouse(Long truthStylerId, PageParam<WarehouseDO> page) {
        LambdaQueryWrapper<WarehouseDO> queryWrapper = Wrappers.lambdaQuery(WarehouseDO.class);

        if (truthStylerId != null) {
            queryWrapper.eq(WarehouseDO::getTruthStylerId, truthStylerId);
        }


        Page<WarehouseDO> pageTemp=page;
        IPage<WarehouseDO> ipage=warehouseMapper.selectPage(pageTemp,queryWrapper);//返回ipage

        return ipage;
    }

    //注释：输入produceDO，先在produce表保存信息；再在warehouse表查找同factory和同styler的信息，若无则创建，若有则叠加次数
    @Override//http://127.0.0.1:8081/wareHouseHandle/saveProduceToWarehouseAndCountPlus
    public void saveProduceToWarehouseAndCountPlus(ProduceDO produceDO) {//{"truthFactoryId":12,"truthStylerId":23,"":111}

        Long truthFactoryId = produceDO.getTruthFactoryId();
        Long truthStylerId = produceDO.getTruthStylerId();
        int produceCount = produceDO.getProduceCount();

        // 构建查询条件
        //QueryWrapper<WarehouseDO> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<WarehouseDO> queryWrapper = Wrappers.lambdaQuery(WarehouseDO.class);

        queryWrapper.eq(WarehouseDO::getTruthFactoryId, truthFactoryId)
                .eq(WarehouseDO::getTruthStylerId, truthStylerId);

        // 查找WarehouseDO记录
        WarehouseDO warehouseDO = warehouseMapper.selectOne(queryWrapper);

        if (warehouseDO != null) {
            // 存在记录，更新stockCount
            warehouseDO.setStockCount(warehouseDO.getStockCount() + produceCount);
            warehouseMapper.updateById(warehouseDO);
        } else {
            // 不存在记录，插入新行
            warehouseDO = new WarehouseDO();
            warehouseDO.setTruthFactoryId(truthFactoryId);
            warehouseDO.setTruthStylerId(truthStylerId);
            warehouseDO.setStockCount(produceCount);
            warehouseMapper.insert(warehouseDO);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void locateStylerFromWarehouse(StylerDTO stylerDTO) {//imp关键函数
//        BasicDO a=new BasicDO();
//        a.setDate(new Date());
//        basicMapper.insert(a);

//        //拿到服务请求的工厂id，样式id，个数
//        //从warehouse中扣除
//        WarehouseDO tempDO = WarehouseDO.builder()
//                .truthStylerId(stylerDTO.getTruthStylerId())
//                .truthFactoryId(stylerDTO.getTruthFactoryId())
//                .stockCount(stylerDTO.getStockCount())
//                .build();
//        //orderItemMapper.insert(tempDO);

        ////
        Long truthFactoryId = stylerDTO.getTruthFactoryId();
        Long truthStylerId = stylerDTO.getTruthStylerId();
        int stockCount = stylerDTO.getUserWantCount();

        // 构建查询条件
        //QueryWrapper<WarehouseDO> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<WarehouseDO> queryWrapper = Wrappers.lambdaQuery(WarehouseDO.class);

        queryWrapper.eq(WarehouseDO::getTruthFactoryId, truthFactoryId)
                .eq(WarehouseDO::getTruthStylerId, truthStylerId);

        // 查找WarehouseDO记录
        WarehouseDO warehouseDO = warehouseMapper.selectOne(queryWrapper);

        if (warehouseDO != null) {
            // 存在记录，更新stockCount
            if(warehouseDO.getStockCount() - stockCount>=0) {
                warehouseDO.setStockCount(warehouseDO.getStockCount() - stockCount);
                warehouseMapper.updateById(warehouseDO);
                //假设此处减少了库存就是已经落库了。我需要解耦其他步骤
            }
            else{
                log.error("！！！！错误：库存不够");
                //参考:throw new ServiceException("支付单创建失败");
                throw new JZHcustomException("扣减库存不足");//配合Transactional回滚
            }
        } else {
            log.error("记录不存在");
            throw new JZHcustomException("记录不存在");
            // 不存在记录，插入新行
//            warehouseDO = new WarehouseDO();
//            warehouseDO.setTruthFactoryId(truthFactoryId);
//            warehouseDO.setTruthStylerId(truthStylerId);
//            warehouseDO.setStockCount(stockCount);
//            warehouseMapper.insert(warehouseDO);
        }

    }

}
