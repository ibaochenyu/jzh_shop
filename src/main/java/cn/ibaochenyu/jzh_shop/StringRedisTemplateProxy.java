package cn.ibaochenyu.jzh_shop;

//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.StringRedisTemplate;


//import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson.JSON;
import jakarta.validation.constraints.NotBlank;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

//@NoArgsConstructor
//@RequiredArgsConstructor
@Component//或者引入CacheAutoConfiguration.java.s
public class StringRedisTemplateProxy  {
    private static final String SAFE_GET_DISTRIBUTED_LOCK_KEY_PREFIX = "safe_get_distributed_lock_get:";
    private final StringRedisTemplate stringRedisTemplate;
    private final RedissonClient redissonClient;

    StringRedisTemplateProxy(StringRedisTemplate str, RedissonClient redissonClient){
        this.stringRedisTemplate=str;
        this.redissonClient = redissonClient;
    }

    public StringRedisTemplate getInstance() {//
        return stringRedisTemplate;
    }

    /**
     * 以一种"安全"的方式获取缓存，如查询结果为空，调用 {@link CacheLoader} 加载缓存
     * 通过此方式防止程序中可能出现的：缓存击穿、缓存雪崩场景，适用于不被外部直接调用的接口
     */
    public <T> T safeGet(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout, TimeUnit timeUnit) {
        return safeGet(key,clazz,cacheLoader,timeout,timeUnit,null);
    }


    //一大坨逻辑，核心总结：
    //T result=stringRedisTemplate.opsForValue().get(key);
//    if(result不空)
//        返回result
    //RLock lock =redissonClient.getLock(key)
//    lock.lock();
//    try {
//        调用第三参数结果load
//        stringRedisTemplate.opsForValue().set
//    }finally {
//        lock.unlock();
//    }
    public <T> T safeGet(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout, TimeUnit timeUnit,CacheGetIfAbsent<String> cacheGetIfAbsent) {
        //return safeGet(key, clazz, cacheLoader, timeout, timeUnit, null);
        T result=get(key,clazz); //核心：stringRedisTemplate.opsForValue().get(key);
        if (!CacheUtil.isNullOrBlank(result))
            return result;
        RLock lock = redissonClient.getLock(SAFE_GET_DISTRIBUTED_LOCK_KEY_PREFIX + key);//核心：redissonClient.getLock
        lock.lock();
        try {
            // 双重判定锁，减轻获得分布式锁后线程访问数据库压力         //yesangl:这里我有文档印象。先get空，再获得尝试
            if (CacheUtil.isNullOrBlank(result = get(key, clazz))) {
                // 如果访问 cacheLoader 加载数据为空，执行后置函数操作
                //if (CacheUtil.isNullOrBlank(result = loadAndSet(key, cacheLoader, timeout, timeUnit, true, bloomFilter))) {//??,不懂不过不重要

                //loadAndSet的核心：stringRedisTemplate.opsForValue().set
                if (CacheUtil.isNullOrBlank(result = loadAndSet(key, cacheLoader, timeout, timeUnit))) {
                    Optional.ofNullable(cacheGetIfAbsent).ifPresent(each -> each.execute(key));////??,不懂不过不重要。其实后续我看：是如果没查到，就继续做什么操作
                }
            }
        }finally {
            lock.unlock();
        }
        return result;
    }


    //核心：
    // （load)调用外部第三参数结果
    // (set)调用put。也就是：stringRedisTemplate.opsForValue().set


    //private <T> T loadAndSet(String key, CacheLoader<T> cacheLoader, long timeout, TimeUnit timeUnit, boolean safeFlag, RBloomFilter<String> bloomFilter) {////ok这个函数
    private <T> T loadAndSet(String key, CacheLoader<T> cacheLoader, long timeout, TimeUnit timeUnit) {////ok这个函数
        T result = cacheLoader.load();//这句话其实就是外部第三参数，（）-》后执行的逻辑。 例如：()->warehouseMapper.selectById(each.getId()),

        //种类的result是直接扔回WarehouseDO类型
        if (CacheUtil.isNullOrBlank(result)) {//如果不是空的，则括号内为false，则继续接下来的逻辑
            return result;
//        }
//        if (safeFlag) {
//            safePut(key, result, timeout, timeUnit, bloomFilter);
//        } else {
//            put(key, result, timeout, timeUnit);
//        }
        }
        put(key, result, timeout, timeUnit);
        return result;
    }

    //核心：stringRedisTemplate.opsForValue().set
    public void put(String key, Object value, long timeout, TimeUnit timeUnit) {
        String actual = value instanceof String ? (String) value : JSON.toJSONString(value);
        stringRedisTemplate.opsForValue().set(key, actual, timeout, timeUnit);//对string操作
    }

//    public <T> T safeGet(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout, TimeUnit timeUnit, RBloomFilter<String> bloomFilter) {
//        return safeGet(key, clazz, cacheLoader, timeout, timeUnit, bloomFilter, null, null);
//    }
//    public <T> T safeGet(String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout, TimeUnit timeUnit,
//                         RBloomFilter<String> bloomFilter, CacheGetFilter<String> cacheGetFilter, CacheGetIfAbsent<String> cacheGetIfAbsent) {



    //核心 stringRedisTemplate.opsForValue().get(key);
    public <T> T get(String key, Class<T> clazz) {
        String value = stringRedisTemplate.opsForValue().get(key);
        if (String.class.isAssignableFrom(clazz)) {//class1.isAssignableFrom(class2) 判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口。如果是则返回 true；否则返回 false。如果该 Class 表示一个基本类型，且指定的 Class 参数正是该 Class 对象，则该方法返回 true；否则返回 false。
            return (T) value;//如果是String的其超类或超接口
        }
        return JSON.parseObject(value, FastJson2Util.buildType(clazz));
    }

}

//2024-06-12T22:53:49.016+08:00  INFO 12696 --- [           main] org.redisson.Version                     : Redisson 3.21.3
//说明的确要@Component注释，好像这样内部就自动@Bean了
