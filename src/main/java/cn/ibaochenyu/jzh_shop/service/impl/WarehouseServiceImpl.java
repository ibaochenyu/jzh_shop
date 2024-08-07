package cn.ibaochenyu.jzh_shop.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Singleton;
import cn.ibaochenyu.jzh_shop.StringRedisTemplateProxy;
import cn.ibaochenyu.jzh_shop.TicketPurchaseRespDTO;
import cn.ibaochenyu.jzh_shop.util.PageParam;
import cn.ibaochenyu.jzh_shop.dao.mapper.BasicMapper;
import cn.ibaochenyu.jzh_shop.dao.mapper.WarehouseMapper;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTO;
import cn.ibaochenyu.jzh_shop.service.WarehouseService;
import cn.ibaochenyu.jzh_shop.webGlobal.JZHcustomException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import cn.ibaochenyu.jzh_shop.AssertMine;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;
import cn.ibaochenyu.jzh_shop.dao.entity.WarehouseDO;
import org.redisson.api.RedissonClient;
import org.springframework.core.io.ClassPathResource;

import java.util.concurrent.TimeUnit;

import static cn.ibaochenyu.jzh_shop.RedisKeyConstant.*;
@Slf4j
@Service
@RequiredArgsConstructor//Required(需要->必须的)-Args-Constructor
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, WarehouseDO> implements WarehouseService {
    private final WarehouseMapper warehouseMapper;
    private final BasicMapper basicMapper;

    private final StringRedisTemplateProxy distributedCache;

    private final RedissonClient redissonClient;

    private final StringRedisTemplateProxy stringRedisTemplateProxy;

    private final OrderMainServiceImpl orderMainService;
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
    @Transactional(rollbackFor = Exception.class)//执行这里的时候，已经tokenBucket并把redis--了
    public TicketPurchaseRespDTO locateStylerFromWarehouse(StylerDTO requestStylerDTO) {//imp关键函数
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


        // 查找reis中WAREHOUSE_INFO_FACTORYID+factoryId的信息，如果没有，就遍历WarehouseDO，查找所有fid+sid+cnt
        StringRedisTemplate stringRedisTemplate=(StringRedisTemplate) stringRedisTemplateProxy.getInstance();
        //为什么是前缀+工厂+style呢--虽然我还没具体看铁路怎么做
        //不过：如果你一个请求打来，3-82003,3-82001,你的redis是工厂，    下有：style和cnt。那么拿redis肯定是根据工厂id先拿，所以就锁这个工厂id的信息
        //我个人猜测读和写用的是同一个锁key
        Boolean ifHas=stringRedisTemplate.hasKey(WAREHOUSE_INFO_FACTORYID+ String.valueOf(requestStylerDTO.getTruthFactoryId()) );
        Map<String,String> maper2=new HashMap<>();
        if(!ifHas){
            RLock lock=redissonClient.getLock(LOCK_WAREHOUSE_INFO_FACTORYID+String.valueOf(requestStylerDTO.getTruthFactoryId()));
            lock.lock();
            try{
                Boolean hasTwoKey=stringRedisTemplate.hasKey(WAREHOUSE_INFO_FACTORYID+ String.valueOf(requestStylerDTO.getTruthFactoryId()) );
                if(!hasTwoKey){

                    //只是查对应fid的数据
                    LambdaQueryWrapper lqb=Wrappers.lambdaQuery(WarehouseDO.class)
                                    .eq(WarehouseDO::getTruthFactoryId,requestStylerDTO.getTruthFactoryId());

                    List<WarehouseDO> lister= warehouseMapper.selectList(lqb);


                    for(WarehouseDO aDO:lister){
                        //maper2.put(aDO.getTruthFactoryId(), aDO.getStockCount());

                        //maper2.put(String.valueOf(aDO.getTruthStylerId()) , JSON.toJSONString( aDO));
                        maper2.put(String.valueOf(aDO.getTruthStylerId()) ,  String.valueOf(aDO.getStockCount()));
                    }


                    //全放是putAll，不是put
                    stringRedisTemplate.opsForHash().putAll(WAREHOUSE_INFO_FACTORYID+ String.valueOf(requestStylerDTO.getTruthFactoryId()),maper2);
                    //.expire和 persist
                    stringRedisTemplate.opsForHash().getOperations().persist(WAREHOUSE_INFO_FACTORYID+ String.valueOf(requestStylerDTO.getTruthFactoryId()));

                }


            }finally{
                lock.unlock();
            }


        }


        //String cnt= (String)stringRedisTemplate.opsForHash().get(WAREHOUSE_INFO_FACTORYID+ String.valueOf(requestStylerDTO.getTruthFactoryId()),requestStylerDTO.getTruthStylerId());
        Map<Object, Object> maper3=stringRedisTemplate.opsForHash().entries(WAREHOUSE_INFO_FACTORYID+ String.valueOf(requestStylerDTO.getTruthFactoryId()));
//        WarehouseDO warehouseDO=new WarehouseDO();
        String str= (String)stringRedisTemplate.opsForHash().get(WAREHOUSE_INFO_FACTORYID+ String.valueOf(requestStylerDTO.getTruthFactoryId()) , String.valueOf(requestStylerDTO.getTruthStylerId()) );
        //fastjson里头的JSON.parseObject
//        WarehouseDO warehouseDO =  JSON.parseObject(str, WarehouseDO.class);
//        warehouseDO.setTruthFactoryId(requestStylerDTO.getTruthFactoryId());
//        warehouseDO.setTruthStylerId(requestStylerDTO.getTruthStylerId());
        String takeCountFromRedis=str;




        //WarehouseDO warehouseDO = warehouseMapper.selectOne(queryWrapper);



        int stockCount = requestStylerDTO.getUserWantCount();

//        WarehouseDO warehouseDO = new WarehouseDO();
//        warehouseDO.setTruthStylerId(requestStylerDTO.getTruthStylerId());
//        warehouseDO.setTruthFactoryId(requestStylerDTO.getTruthFactoryId());
//        warehouseDO.setStockCount(requestStylerDTO.getUserWantCount());
//
//        // 构建查询条件
//        //QueryWrapper<WarehouseDO> queryWrapper = new QueryWrapper<>();
//        LambdaQueryWrapper<WarehouseDO> queryWrapper = Wrappers.lambdaQuery(WarehouseDO.class);
//        queryWrapper.eq(WarehouseDO::getTruthFactoryId, requestStylerDTO.getTruthFactoryId())
//                .eq(WarehouseDO::getTruthStylerId, requestStylerDTO.getTruthStylerId());

        int rtUpdateCnt=-1;
        Long changeId=-1L;
        if (takeCountFromRedis != null) {
            // 存在记录，更新stockCount
            //铁路是先锁锁的状态，并没有对数据库的库存直接减去么？？？
            if( Integer.valueOf(takeCountFromRedis).intValue()  - stockCount>=0) {
//                warehouseDO.setStockCount(warehouseDO.getStockCount() - stockCount);

                //此处不应该直接把cnt扔进去把
                //或者cnt--
                //虽然我没验证过铁路怎么样
//                rtUpdateCnt=warehouseMapper.updateById(warehouseDO);
//                changeId=warehouseDO.getId();

                int maybeUpdateCnt=warehouseMapper.updateWareHouseItsCount_UseStylerDTO(requestStylerDTO);


                orderMainService.saveStylerToOrderMain(requestStylerDTO);//requestStylerDTO和stylerDTO都是StylerDTO类型，所以直接扔

                TicketPurchaseRespDTO temp=new TicketPurchaseRespDTO();
                //temp.setRtUpdateCnt(rtUpdateCnt);
                temp.setRtUpdateCnt(maybeUpdateCnt);
                temp.setChangeId(changeId);
                return temp;
                //假设此处减少了库存就是已经落库了。我需要解耦其他步骤
            }
//            if(warehouseDO.getStockCount() - stockCount>=0) {
//                warehouseDO.setStockCount(warehouseDO.getStockCount() - stockCount);
//                rtUpdateCnt=warehouseMapper.updateById(warehouseDO);
//                changeId=warehouseDO.getId();
//                TicketPurchaseRespDTO temp=new TicketPurchaseRespDTO();
//                temp.setRtUpdateCnt(rtUpdateCnt);
//                temp.setChangeId(changeId);
//                return temp;
//                //假设此处减少了库存就是已经落库了。我需要解耦其他步骤
//            }
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


    @Override//查看token是否能拿，能拿则顺便把reids数目--
    public boolean takeTokenFromBucket(StylerDTO requstStylerDTO){


        String factIdStr=String.valueOf(requstStylerDTO.getTruthFactoryId());



        //查看WAREHOUSE_INFO_FACTORYID是否在redis存在，没有则去数据库查找某个特定fid，并构造fid-sid-cnt
        StringRedisTemplate stringRedisTemplate=(StringRedisTemplate)distributedCache.getInstance();
        //不是stringRedisTemplate.get判断有没有，而是stringRedisTemplate.hashKey
        Boolean hasKey=stringRedisTemplate.hasKey(WAREHOUSE_INFO_FACTORYID+factIdStr);
        if(!hasKey){
            RLock lock=redissonClient.getLock(LOCK_WAREHOUSE_INFO_FACTORYID);
            lock.lock();
            try{
                Boolean hasTwoKey=stringRedisTemplate.hasKey(WAREHOUSE_INFO_FACTORYID+factIdStr);
                if(!hasTwoKey){
                    //先筛选getTruthFactoryId
                    LambdaQueryWrapper<WarehouseDO> queryWrapper= Wrappers.lambdaQuery(WarehouseDO.class)
                            .eq(WarehouseDO::getTruthFactoryId,requstStylerDTO.getTruthFactoryId());
                    List<WarehouseDO> lister3=warehouseMapper.selectList(queryWrapper);
                    Map<Object,Object> maper2=new HashMap<>();

                    //获取该factory下所有styler
                    for(WarehouseDO each:lister3 ){
//                        WarehouseDO warehouseDO=distributedCache.safeGet(WAREHOUSE_INFO_FACTORYID +each.getTruthFactoryId()
//                                ,WarehouseDO.class,
//                                ()->warehouseMapper.selectById(each.getId()),
//                                ADVANCE_TICKET_DAY,
//                                TimeUnit.DAYS);
                        maper2.put(String.valueOf(each.getTruthStylerId()),String.valueOf(each.getStockCount()));
                    }
                    stringRedisTemplate.opsForHash().putAll(WAREHOUSE_INFO_FACTORYID+factIdStr,maper2);
                }

            }finally{
                lock.unlock();
            }
        }


        String LUA_TEST="lua/test.lua";
        DefaultRedisScript<Long> actual = Singleton.get(LUA_TEST, () -> {
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(LUA_TEST)));
            redisScript.setResultType(Long.class);
            return redisScript;
        });



        //送入jzh:factoyId_trueFactoryId_3，送入style:82003,送入数量101
        AssertMine.notNull(actual);
        String actualHashKey="jzh:warehouse_info:"+String.valueOf(factIdStr);//jzh:factoyId_trueFactoryId_3
        String stylerKey=String.valueOf(requstStylerDTO.getTruthStylerId());
        String userWantCount=String.valueOf(requstStylerDTO.getUserWantCount()) ;
        Long result =stringRedisTemplate.execute(actual, Lists.newArrayList(actualHashKey, stylerKey), userWantCount);//stringRedisTemplate.execute实际就是执行如redis的脚本
        //Long result=(Long)  (ArrayList<Object>)resultArray.get(4);

        Boolean rt= (result != null && Objects.equals(result, 0L));//这里key传入两个对象 //actualHashKey：index12306-ticket-service:ticket_availability_token_bucket:1    还有  luaScriptKey：北京南_杭州东
//result结果是一个Arrayrlist，restlt.get(0)
//仿写：takeTokenFromBucket

        int a=2;
        int b=3;

        return rt;
    }

    @Override
    public TicketPurchaseRespDTO executePurchaseTickets(StylerDTO stylerDTO){
        TicketPurchaseRespDTO rt=locateStylerFromWarehouse(stylerDTO);
        return rt;
    }
}
