package cn.ibaochenyu.jzh_shop;

//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.StringRedisTemplate;


//import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

//@NoArgsConstructor
//@RequiredArgsConstructor
@Component//或者引入CacheAutoConfiguration.java.s
public class StringRedisTemplateProxy  {
    private final StringRedisTemplate stringRedisTemplate;

    StringRedisTemplateProxy(StringRedisTemplate str){
        this.stringRedisTemplate=str;
    }

    public StringRedisTemplate getInstance() {//
        return stringRedisTemplate;
    }
}

//2024-06-12T22:53:49.016+08:00  INFO 12696 --- [           main] org.redisson.Version                     : Redisson 3.21.3
//说明的确要@Component注释，好像这样内部就自动@Bean了
