package cn.ibaochenyu.jzh_shop.controller;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.map.MapUtil;
import cn.ibaochenyu.jzh_shop.Assert;
import cn.ibaochenyu.jzh_shop.StringRedisTemplateProxy;
import cn.ibaochenyu.jzh_shop.TicketPurchaseRespDTO;
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
import cn.ibaochenyu.jzh_shop.webGlobal.JZHcustomException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

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

    private final ConfigurableEnvironment environment;


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


    private final Cache<String, ReentrantLock> localLockMap = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.DAYS) ////我们通过 ConcurrentHashMap 存储每个列车的本地锁，作为申请分布式锁之前的一层性能挡板，隔绝无效流量请求 Redis。但是大家发现没有，这个 ConcurrentHashMap 是只能存储，但是没有任何过期策略。这样会导致一个问题就是应用长时间不发布，越来越多的列车数据存储在容器中，直到内存溢出为止。
            .build(); ////通过 Caffeine 创建本地安全锁容器，Caffeine 的 expireAfterWrite 方法代表，放入元素过期的时间是什么。比如咱们以下案例中配置的一天过期，代表一个列车的本地公平锁创建一天后失效。
//Caffeine 缓存是线程安全的，它确保在多线程环境下正确处理并发操作。因此，localLockMap 中的操作（如 put）是原子性的，并且所有线程都会看到一致的缓存状态。

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


//        List<ReentrantLock> localLockList = new ArrayList<>();//ReentrantLock在sync.state里头，0代表不锁，1代表加锁
        List<RLock> distributedLockList = new ArrayList<>();


        if(count>0){//送入“杭州三鑫”，送出这个工厂的id
        //if(true){//送入“杭州三鑫”，送出这个工厂的id
            log.info("或许没有这个key啊");//RLock.lock (); 是阻塞式等待的，默认加锁时间是30s


            //创建本地锁 //虽然 ReentrantLock 不直接支持查看过期时间，但
//            ReentrantLock localLock = localLockMap.getIfPresent(LOCALLOCK_FACTORYNAMEFORUSER_TRUEFACTORYID_MAPPING);
//                    //第二个线程，认为localLock是存在的。说明localLockMap是以整个进程池为单位，
//            if (localLock == null) {//当没有本地锁时候，创建一个
//                log.info("localLock==null");
//                synchronized (WarehouseController.class) {
//                    log.info("进入synchronized");
//                    if ((localLock = localLockMap.getIfPresent(LOCALLOCK_FACTORYNAMEFORUSER_TRUEFACTORYID_MAPPING)) == null) {//大概：再次确认没有本地锁
//                        localLock = new ReentrantLock(true);//如果没有本地锁，则创建一个
//                        localLockMap.put(LOCALLOCK_FACTORYNAMEFORUSER_TRUEFACTORYID_MAPPING, localLock);//本地锁会一天之后过期
//                    }
//                    log.info("开始sleep");//这里ttl从30到20，再回到30
//                    try {//internalLockLeaseTime默认30
//                        Thread.sleep(1000*60);//Watch Dog 机制其实就是一个后台定时任务线程，获取锁成功之后，会将持有锁的线程放入到一个 RedissonLock.EXPIRATION_RENEWAL_MAP里面，然后每隔 10 秒 （internalLockLeaseTime / 3） 检查一下，
//                    } catch (InterruptedException e) {//https://juejin.cn/post/7044833565766320164
//                        throw new RuntimeException(e);
//                    }
//                    log.info("结束sleep");
//                }
//            }
//            localLockList.add(localLock);//我不明白这段逻辑，为什么这里要加两个锁：根据“手摸手之实现v2版本列车购票流程”，让线程先取竞争本地服务的内部锁，再取竞争分布式锁，从而减少redis的压力



            //创建分布式锁
            RLock lock =redissonClient.getLock(LOCK_FACTORYNAMEFORUSER_TRUEFACTORYID_MAPPING);
            distributedLockList.add(lock);

            //为什么组成list，lock就要放到try里头？？
            //lock.lock();
            try {
//                localLockList.forEach(ReentrantLock::lock);
                distributedLockList.forEach(RLock::lock);


                wantFactoryIdDetail=stringRedisTemplate.opsForHash().multiGet(FACTORYNAMEFORUSER_TRUEFACTORYID_MAPPING,Lists.newArrayList(requestParamsStylerDTOForUser.getFactoryNameForUser(),"杭州三鑫工业园"));
                count=wantFactoryIdDetail.stream().filter(Objects::isNull).count();
//                log.info("开始sleep");//这里ttl从30到20，再回到30
//                try {//internalLockLeaseTime默认30
//                    Thread.sleep(1000*40);//Watch Dog 机制其实就是一个后台定时任务线程，获取锁成功之后，会将持有锁的线程放入到一个 RedissonLock.EXPIRATION_RENEWAL_MAP里面，然后每隔 10 秒 （internalLockLeaseTime / 3） 检查一下，
//                } catch (InterruptedException e) {//https://juejin.cn/post/7044833565766320164
//                    throw new RuntimeException(e);
//                }
//                log.info("结束sleep");
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
//                lock.unlock();

//                localLockList.forEach(mlocalLock -> {
//                    try {
//                        mlocalLock.unlock();
//                    } catch (Throwable ignored) {
//                    }
//                });

                distributedLockList.forEach(distributedLock -> {
                    try {
                        distributedLock.unlock();
                    } catch (Throwable ignored) {
                    }
                });
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
        String factoryID_trueFactoryID_count_key = String.format(WAREHOUSE_INFO_FACTORYID, wantFactoryIdDetail.get(0));
        //以前是MultiGet（返回List<Object>），现在是用entries拿全部（返回Map<Object, Object>）
        Map<Object, Object> maper2=stringRedisTemplate.opsForHash().entries(factoryID_trueFactoryID_count_key);
        if (MapUtil.isEmpty(maper2)) {
            RLock lock=redissonClient.getLock(LOCK_WAREHOUSE_INFO_FACTORYID);
            lock.lock();
            try{
                maper2=stringRedisTemplate.opsForHash().entries(factoryID_trueFactoryID_count_key);
                if(MapUtil.isEmpty(maper2)){
                    LambdaQueryWrapper<WarehouseDO> queryWrapper= Wrappers.lambdaQuery(WarehouseDO.class)
                            .eq(WarehouseDO::getTruthFactoryId,wantFactoryIdDetail.get(0));
                    List<WarehouseDO> lister3=warehouseMapper.selectList(queryWrapper);//获取WarehouseDO表中,factoryID和用户请求翻译过来的id一样的信息
                    //此处获取后，限定好了WareHouse表中仅仅factory=1的信息。在这些信息中，仅仅

                    for(WarehouseDO each:lister3 ){//对于一个factory，查询不同的style，看看之前有没有查询过

                        //由于它是查page，所以缓存很可能没有，因此这里存factoryID来
                        //由于不像铁路一样查询其他表，所以在这里distributedCache.safeGet不用
                        WarehouseDO warehouseDO=distributedCache.safeGet(WAREHOUSE_INFO_FACTORYID +each.getTruthFactoryId()
                                ,WarehouseDO.class,
                                ()->warehouseMapper.selectById(each.getId()),
                                ADVANCE_TICKET_DAY,
                                TimeUnit.DAYS);
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

    @GetMapping("takeTokenFromBucket")//{"truthFactoryId":3,"truthStylerId":82003,"userWantCount":80}
    public Boolean takeTokenFromBucket(@RequestBody StylerDTO requstStylerDTO){


        Boolean rt=warehouseService.takeTokenFromBucket(requstStylerDTO);
        return rt;


        //return ServerResponseEntity.success();
    }

    @PutMapping("purchaseTicketsV2")
    public ServerResponseEntity<TicketPurchaseRespDTO> purchaseTicketsV2(@RequestBody StylerDTO requestStylerDTO){
        boolean tokenResult =  warehouseService.takeTokenFromBucket(requestStylerDTO);
        if (!tokenResult) {
            throw new JZHcustomException("列车站点已无余票");
        }

        List<ReentrantLock> localLockList = new ArrayList<>();//ReentrantLock是一个可重入的互斥锁，又被称为“独占锁”。
        List<RLock> distributedLockList = new ArrayList<>();
        TicketPurchaseRespDTO rt=new TicketPurchaseRespDTO();

        String lockKey = environment.resolvePlaceholders(String.format(LOCK_PURCHASE_TICKETS_V2, requestStylerDTO.getTruthFactoryId(), requestStylerDTO.getTruthStylerId()));//index12306-ticket-service:lock:purchase_tickets_2_1
        ReentrantLock localLock = localLockMap.getIfPresent(lockKey);//ReentrantLock是一个可重入的互斥锁，又被称为“独占锁”。ReentrantLock锁在同一个时间点只能被一个线程锁持有
        if (localLock == null) {//当没有本地锁时候，创建一个
            synchronized (WarehouseController.class) {
                if ((localLock = localLockMap.getIfPresent(lockKey)) == null) {//大概：再次确认没有本地锁
                    localLock = new ReentrantLock(true);//如果没有本地锁，则创建一个
                    localLockMap.put(lockKey, localLock);//本地锁会一天之后过期
                }
            }
        }
        localLockList.add(localLock);
        RLock distributedLock = redissonClient.getFairLock(lockKey);
        distributedLockList.add(distributedLock);
        try {
            //这里有问题的，会死锁。不过如果你是一个种类传进来，就没事了
            localLockList.forEach(ReentrantLock::lock);
            distributedLockList.forEach(RLock::lock);
            rt=warehouseService.executePurchaseTickets(requestStylerDTO);
        } finally {
            localLockList.forEach(mlocalLock -> {
                try {
                    mlocalLock.unlock();
                } catch (Throwable ignored) {
                }
            });
            distributedLockList.forEach(mdistributedLock -> {
                try {
                    mdistributedLock.unlock();
                } catch (Throwable ignored) {
                }
            });
        }


        return ServerResponseEntity.success(rt);
    }



    @GetMapping("testSyschronized")
    public ServerResponseEntity<Void> testSyschronized(){
        log.info("进入testSyschronized");
        synchronized (WarehouseController.class) {
            log.info("开始sleep");
            try {
                Thread.sleep(1000*60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("结束sleep");
        }
        log.info("结束testSyschronized");

        return ServerResponseEntity.success();
    }
//2024-06-13T16:55:08.549+08:00  INFO 104888 --- [nio-8081-exec-1] c.i.j.controller.WarehouseController     : 进入testSyschronized
//2024-06-13T16:55:08.549+08:00  INFO 104888 --- [nio-8081-exec-1] c.i.j.controller.WarehouseController     : 开始sleep
//2024-06-13T16:55:12.916+08:00  INFO 104888 --- [nio-8081-exec-2] c.i.j.controller.WarehouseController     : 进入testSyschronized
//2024-06-13T16:56:08.558+08:00  INFO 104888 --- [nio-8081-exec-1] c.i.j.controller.WarehouseController     : 结束sleep
//2024-06-13T16:56:08.558+08:00  INFO 104888 --- [nio-8081-exec-1] c.i.j.controller.WarehouseController     : 结束testSyschronized
//2024-06-13T16:56:08.558+08:00  INFO 104888 --- [nio-8081-exec-2] c.i.j.controller.WarehouseController     : 开始sleep
//    在Java中，synchronized关键字用于同步代码块或方法。它确保同一时刻只有一个线程可以执行被同步的代码
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


//以及内嵌Tomcat容器用于处理HTTP请求的线程池。
//：Spring Boot内部使用的异步任务执行的线程池（通常指的是通过@Async注解定义的异步任务，或者是直接注入自定义的线程池进行使用）


//server.tomcat.max-threads：最大工作线程数，默认200,
//        server.tomcat.min-spare-threads：最小工作线程数，初始化分配线程数，默认10

//默认设置中，Tomcat的最大线程数是200，最大连接数是10000。支持的并发量是指连接数，200个线程如何处理10000条连接的？
//        目前Tomcat有三种处理连接的模式，一种是BIO，一个线程只处理一个连接，另一种就是NIO，一个线程处理多个连接。由于HTTP请求不会太耗时，而且多个连接一般不会同时来消息，所以一个线程处理多个连接没有太大问题。
//        nio-8081-exec-2，所以此时tomcat是NIO模式

//Tomcat创建线程池的时候底层还是利用JDK的ThreadPoolExecutor


//Spring Boot在没有自定义线程池配置的情况下，会自动配置一个ThreadPoolTaskExecutor作为默认线程池。根据官方文档和相关资源，以下是默认的线程池参数：
//
//核心线程数 (corePoolSize): 8
//最大线程数 (maxPoolSize): Integer.MAX_VALUE (无限制)
//队列容量 (queueCapacity): Integer.MAX_VALUE (无限制)
//空闲线程保留时间 (keepAliveSeconds): 60秒
//线程池拒绝策略 (RejectedExecutionHandler): AbortPolicy（默认策略，超出线程池容量和队列容量时抛出RejectedExecutionException异常）
//这些参数可以通过在application.properties或application.yml文件中设置来进行自定义调整。例如：
//
//# 核心线程数，默认为8
//spring.task.execution.pool.core-size
//# 最大线程数，默认为Integer.MAX_VALUE
//spring.task.execution.pool.max-size
//# 任务等待队列容量，默认为Integer.MAX_VALUE
//spring.task.execution.pool.queue-capacity
//# 空闲线程等待时间，默认为60s。如果超过这个时间没有任务调度，则线程会被回收
//spring.task.execution.pool.keep-alive
//# 是否允许回收空闲的线程，默认为true
//spring.task.execution.pool.allow-core-thread-timeout
//# 线程名前缀
//spring.task.execution.thread-name-prefix=task-

//https://blog.csdn.net/belongtocode/article/details/138764109
