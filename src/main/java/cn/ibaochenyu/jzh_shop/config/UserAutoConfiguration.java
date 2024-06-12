package cn.ibaochenyu.jzh_shop.config;


import cn.ibaochenyu.jzh_shop.RequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//https://blog.csdn.net/weixin_45755816/article/details/121424751
//使用时候，单@Bean不行
//得@Configuration+@Bean


//@ConditionalOnWebApplication
@Configuration
public class UserAutoConfiguration {
//    @Autowired
//    RequestFilter requestFilter;

    /**
     * 用户信息传递过滤器
     */
    @Bean
    public FilterRegistrationBean<RequestFilter> globalUserTransmitFilter() {//globalUserTransmitFilter这个名字应该是自己自定义的，不是.s
        FilterRegistrationBean<RequestFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RequestFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(100);
        return registration;
    }
}
