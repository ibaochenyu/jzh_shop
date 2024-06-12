package cn.ibaochenyu.jzh_shop.controller;

import cn.ibaochenyu.jzh_shop.StringRedisTemplateProxy;
import cn.ibaochenyu.jzh_shop.dao.entity.FactoryDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.FactoryMapper;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTOForUser;
import cn.ibaochenyu.jzh_shop.util.PageParam;
import cn.ibaochenyu.jzh_shop.myResponse.ServerResponseEntity;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;
import cn.ibaochenyu.jzh_shop.dao.entity.WarehouseDO;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTO;
import cn.ibaochenyu.jzh_shop.service.WarehouseService;
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

import static cn.ibaochenyu.jzh_shop.RedisKeyConstant.FACTORYNAMEFORUSER_TRUEFACTORYID_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wareHouseHandle")
@Slf4j
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final StringRedisTemplateProxy distributedCache;

    private final RedissonClient redissonClient;
    private final FactoryMapper factoryMapper;


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

    //参考public TicketPageQueryRespDTO pageListTicketQueryV1(TicketPageQueryReqDTO requestParam) {
    @PutMapping("locateStylerFromWarehouseWithUserStyleInput")//等价于PurchatTicketV1
    public ServerResponseEntity<Void> locateStylerFromWarehouseWithUserStyleInput(@RequestBody StylerDTOForUser requestParamsStylerDTOForUser){

        StringRedisTemplate stringRedisTemplate=(StringRedisTemplate)distributedCache.getInstance();//obejct强制转化为StringRedisTemplate
        List<Object> factoryNameDetail=stringRedisTemplate.opsForHash().multiGet(FACTORYNAMEFORUSER_TRUEFACTORYID_MAPPING,Lists.newArrayList(requestParamsStylerDTOForUser.getFactoryNameForUser(),"4"));
        long count=factoryNameDetail.stream().filter(Objects::isNull).count();
        if(count>0){
            log.info("或许没有这个key啊");
            RLock lock =redissonClient.getLock(FACTORYNAMEFORUSER_TRUEFACTORYID_MAPPING);
            lock.lock();
            try {
                factoryNameDetail=stringRedisTemplate.opsForHash().multiGet(FACTORYNAMEFORUSER_TRUEFACTORYID_MAPPING,Lists.newArrayList(requestParamsStylerDTOForUser.getFactoryNameForUser(),"4"));
                count=factoryNameDetail.stream().filter(Objects::isNull).count();
                if(count>0){//再次检验 //wrapper  包装者
                    List<FactoryDO> factoryDOlist= factoryMapper.selectList(Wrappers.emptyWrapper());
                    //Map<String, Long> maper = new HashMap<>(); 这种放不进去啊
                    Map<String, String> maper = new HashMap<>();
                    factoryDOlist.forEach(each->maper.put(each.getFactoryName(),  String.valueOf(each.getId()) ));//获取所有可能的取值
                    stringRedisTemplate.opsForHash().putAll(FACTORYNAMEFORUSER_TRUEFACTORYID_MAPPING,maper);//拿到开始站和结束站对应的名称
                    factoryNameDetail = new ArrayList<>();
                    factoryNameDetail.add(maper.get(requestParamsStylerDTOForUser.getFactoryNameForUser()));
                    factoryNameDetail.add(maper.get("4"));
                    System.out.println("path1"+factoryNameDetail.toString());
                }
            }finally {
                lock.unlock();
            }
        }
        else{
            System.out.println("path2");
            log.info("有这个key");
        }

        int a=3;
        int b=4;


        return ServerResponseEntity.success();
    }


}

//
//{
//        "truthFactoryId":12,
//        "truthStylerId":83009,
//        "stockCount":34
//        }
