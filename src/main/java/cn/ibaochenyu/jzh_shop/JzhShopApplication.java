package cn.ibaochenyu.jzh_shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication

//2024-05-21T11:54:54.054+08:00 ERROR 37800 --- [nio-8080-exec-8] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Handler dispatch failed: java.lang.NoClassDefFoundError: Could not initialize class cn.ibaochenyu.jzh_shop.toolkit.BeanUtil] with root cause
//@SpringBootApplication(scanBasePackages = {
//        "cn.ibaochenyu.jzh_shop.toolkit",
//})
@MapperScan("cn.ibaochenyu.jzh_shop.dao.mapper")

public class JzhShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JzhShopApplication.class, args);
    }

}
