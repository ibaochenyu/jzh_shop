package cn.ibaochenyu.jzh_shop.controller;

import cn.hutool.core.map.MapUtil;
import cn.ibaochenyu.jzh_shop.StringRedisTemplateProxy;
import cn.ibaochenyu.jzh_shop.dao.entity.FactoryDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.FactoryMapper;
import cn.ibaochenyu.jzh_shop.dao.mapper.WarehouseMapper;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTOForUser;
import cn.ibaochenyu.jzh_shop.util.PageParam;
import cn.ibaochenyu.jzh_shop.myResponse.ServerResponseEntity;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;
import cn.ibaochenyu.jzh_shop.dao.entity.WarehouseDO;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTO;
import cn.ibaochenyu.jzh_shop.service.WarehouseService;
import cn.ibaochenyu.jzh_shop.util.debugParam;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static cn.ibaochenyu.jzh_shop.RedisKeyConstant.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wareHouseHandle")
@Slf4j
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final StringRedisTemplateProxy distributedCache;

    private final RedissonClient redissonClient;
    private final FactoryMapper factoryMapper;

    private final WarehouseMapper warehouseMapper;


    @PostMapping("saveWarehouse")
    public ServerResponseEntity<Void> saveWarehouse(@RequestBody WarehouseDO aDO){//本来传参无法进来，把其他人的import拿来就好了
        warehouseService.mySave(aDO);
        return ServerResponseEntity.success();
    }


    //saveProduceToWarehouseAndCountPlus和saveWarehouseAddCount差不多。参数输入不同；一个操作一张表，一个操作两张表
    @PostMapping("saveProduceToWarehouseAndCountPlus")
    public ServerResponseEntity<Void> saveProduceToWarehouseAndCountPlus(@RequestBody ProduceDO aDO){//本来传参无法进来，把其他人的import拿来就好了
        warehouseService.saveProduceToWarehouseAndCountPlus(aDO);
        return ServerResponseEntity.success();
    }

    @PutMapping("saveWarehouseAddCount")
    public ServerResponseEntity<Void> saveWarehouseAddCount(@RequestBody WarehouseDO aDO){//本来传参无法进来，把其他人的import拿来就好了
        warehouseService.mySave(aDO);
        return ServerResponseEntity.success();
    }

    @GetMapping//truthStylerId  commodityStatus
    public ServerResponseEntity<IPage<WarehouseDO>> getPageWarehouse(@RequestParam(name="truthStylerId", required = false) Long truthStylerId, PageParam<WarehouseDO> page){
        IPage<WarehouseDO> rt=warehouseService.getPageWareHouse(truthStylerId,page);
        //List<CommodityDO> tempList;
        //tempList.add(new CommodityDO())
        return ServerResponseEntity.success(rt);
    }

    @PutMapping("locateStylerFromWarehouse")
    public ServerResponseEntity<Void> locateStylerFromWarehouse(@RequestBody StylerDTO stylerDTO){
        warehouseService.locateStylerFromWarehouse(stylerDTO);
        return ServerResponseEntity.success();
    }
//期望程序启动从60秒到10.568 seconds

//实现好了redis加速查询
    //输入StylerDTOForUser,也就是factorName,stylerid,count
    //返回该factory下，不同stylerid的count
    @GetMapping("pageListTicketQueryV1")//等价于pageListTicketQueryV1
    public ServerResponseEntity<debugParam<Map<Object,Object>>> pageListTicketQueryV1(@RequestBody StylerDTOForUser requestParamsStylerDTOForUser){
//购票V1，传入BJP代码，翻译成北京，再得到北京南到XXX的时间、地点等信息

        Integer flag1=-1;
        Integer flag2=-1;

        StringRedisTemplate stringRedisTemplate=(StringRedisTemplate)distributedCache.getInstance();//obejct强制转化为StringRedisTemplate
        List<Object> wantFactoryIdDetail=stringRedisTemplate.opsForHash().multiGet(FACTORYNAMEFORUSER_TRUEFACTORYID_MAPPING,Lists.newArrayList(requestParamsStylerDTOForUser.getFactoryNameForUser(),"杭州三鑫工业园"));
        long count=wantFactoryIdDetail.stream().filter(Objects::isNull).count();
        if(count>0){//送入“杭州三鑫”，送出这个工厂的id
            log.info("或许没有这个key啊");//RLock.lock (); 是阻塞式等待的，默认加锁时间是30s
            RLock lock =redissonClient.getLock(LOCK_FACTORYNAMEFORUSER_TRUEFACTORYID_MAPPING);
            lock.lock();
            try {
                wantFactoryIdDetail=stringRedisTemplate.opsForHash().multiGet(FACTORYNAMEFORUSER_TRUEFACTORYID_MAPPING,Lists.newArrayList(requestParamsStylerDTOForUser.getFactoryNameForUser(),"杭州三鑫工业园"));
                count=wantFactoryIdDetail.stream().filter(Objects::isNull).count();
                log.info("开始sleep");//这里ttl从30到20，再回到30
                try {//internalLockLeaseTime默认30
                    Thread.sleep(1000*40);//Watch Dog 机制其实就是一个后台定时任务线程，获取锁成功之后，会将持有锁的线程放入到一个 RedissonLock.EXPIRATION_RENEWAL_MAP里面，然后每隔 10 秒 （internalLockLeaseTime / 3） 检查一下，
                } catch (InterruptedException e) {//https://juejin.cn/post/7044833565766320164
                    throw new RuntimeException(e);
                }
                log.info("结束sleep");
                if(count>0){//再次检验 //wrapper  包装者
                    List<FactoryDO> factoryDOlist= factoryMapper.selectList(Wrappers.emptyWrapper());
                    //Map<String, Long> maper = new HashMap<>(); 这种放不进去啊
                    Map<String, String> maper = new HashMap<>();
                    factoryDOlist.forEach(each->maper.put(each.getFactoryName(),  String.valueOf(each.getId()) ));//获取所有可能的取值
                    stringRedisTemplate.opsForHash().putAll(FACTORYNAMEFORUSER_TRUEFACTORYID_MAPPING,maper);//拿到开始站和结束站对应的名称//此处默认不过期
                    wantFactoryIdDetail = new ArrayList<>();//lock默认30秒过期？？？
                    wantFactoryIdDetail.add(maper.get(requestParamsStylerDTOForUser.getFactoryNameForUser()));
                    wantFactoryIdDetail.add(maper.get("杭州三鑫工业园"));
                    System.out.println("path1"+wantFactoryIdDetail.toString());
                    flag1=1;
                }
            }finally {
                lock.unlock();
            }
        }
        else{
            flag1=2;
            //System.out.println("path2");
            log.info("key1已经存在");
        }

        log.info("某个thread进入中间过程了");
        int a=3;
        int b=4;

        //加下来列车是拿列车id来锁//而我是拿工厂id来锁
        String factoryID_trueFactoryID_count_key = String.format(FACTORYID_STYLERID_COUNT, wantFactoryIdDetail.get(0));
        //以前是MultiGet（返回List<Object>），现在是用entries拿全部（返回Map<Object, Object>）
        Map<Object, Object> maper2=stringRedisTemplate.opsForHash().entries(factoryID_trueFactoryID_count_key);
        if (MapUtil.isEmpty(maper2)) {
            RLock lock=redissonClient.getLock(LOCK_FACTORYID_STYLERID_COUNT);
            lock.lock();
            try{
                maper2=stringRedisTemplate.opsForHash().entries(factoryID_trueFactoryID_count_key);
                if(MapUtil.isEmpty(maper2)){
                    LambdaQueryWrapper<WarehouseDO> queryWrapper= Wrappers.lambdaQuery(WarehouseDO.class)
                            .eq(WarehouseDO::getTruthFactoryId,wantFactoryIdDetail.get(0));
                    List<WarehouseDO> lister3=warehouseMapper.selectList(queryWrapper);//获取WarehouseDO表中,factoryID和用户请求翻译过来的id一样的信息
                    //此处获取后，限定好了WareHouse表中仅仅factory=1的信息。在这些信息中，仅仅

                    for(WarehouseDO each:lister3 ){//对于一个factory，查询不同的style，看看之前有没有查询过

                        //由于不像铁路一样查询其他表，所以在这里distributedCache.safeGet不用
//                        WarehouseDO warehouseDO=distributedCache.safeGet(WAREHOUSE_INFO+each.getTruthFactoryId()+each.getTruthStylerId()
//                                ,WarehouseDO.class,
//                                ()->warehouseMapper.selectById(each.getId()),
//                                ADVANCE_TICKET_DAY,
//                                TimeUnit.SECONDS);
                        maper2.put(String.valueOf(each.getTruthStylerId()),String.valueOf(each.getStockCount()));
                    }
                    stringRedisTemplate.opsForHash().putAll(factoryID_trueFactoryID_count_key,maper2);
                    flag2=1;
                }

            }
            finally {
                lock.unlock();;
            }
        }
        else{
            log.info("key2已经存在");
            flag2=2;
        }
        debugParam<Map<Object,Object>> temp = new debugParam<Map<Object,Object>>();
        temp.setDebug1(String.valueOf(flag1) );
        temp.setDebug2(String.valueOf(flag2));
        temp.setOriData(maper2);
        return ServerResponseEntity.success(temp);
    }

    @PutMapping("executePurchaseWarehouse")
    public ServerResponseEntity<Void> executePurchaseWarehouse(@RequestBody StylerDTOForUser requestParamsStylerDTOForUser){
        return ServerResponseEntity.success();
    }

}

//
//{
//        "truthFactoryId":12,
//        "truthStylerId":83009,
//        "stockCount":34
//        }


//code1
//RLock lock =redissonClient.getLock
//lock.lock();
//code2
//
//如果lock.lock()没有得到lock，它会一直等待，再执行code2；还是直接不等待，直接执行code2?
//
//lock.lock() 会一直等待，直到获取到锁为止。在获取到锁之前，它不会执行 code2。
//如果你希望在获取不到锁的时候直接执行 code2，可以使用 tryLock() 方法，它会尝试获取锁，如果获取不到不会阻塞当前线程，而是返回一个布尔值表示是否成功获取到锁。例如：
//
//RLock lock = redissonClient.getLock("yourLockName");
//if (lock.tryLock()) {
//try {
//// 执行需要加锁的代码
//code2;
//} finally {
//lock.unlock();
//}
//} else {
//// 无法获取锁时执行的代码
//code2;
//}


//2024-06-13T15:52:36.115+08:00  INFO 37268 --- [nio-8081-exec-4] c.i.j.controller.WarehouseController     : 或许没有这个key啊
//2024-06-13T15:52:36.120+08:00  INFO 37268 --- [nio-8081-exec-4] c.i.j.controller.WarehouseController     : 开始sleep
//2024-06-13T15:52:44.621+08:00  INFO 37268 --- [nio-8081-exec-5] c.i.j.controller.WarehouseController     : 或许没有这个key啊
//2024-06-13T15:53:16.126+08:00  INFO 37268 --- [nio-8081-exec-4] c.i.j.controller.WarehouseController     : 结束sleep
//[nio-8081-exec-5]  线程编号5：在执行
