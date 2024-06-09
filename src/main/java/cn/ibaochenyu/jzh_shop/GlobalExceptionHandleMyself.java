package cn.ibaochenyu.jzh_shop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice//务必加这个注释，否则无法拦截
public class GlobalExceptionHandleMyself {
    @ExceptionHandler(value = Throwable.class)
    public ServerResponseEntity defaultErrorHandle(HttpServletRequest hsr,Exception e){
        log.error("进入GlobalExceptionHandleMyself关于Throwable的处理：{}",e.getMessage());
        return ServerResponseEntity.fail();//我看过了，铁路注册的用户名如果重复，首部状态码是200，responce的错误码才是自己细节定义的
    }//此处定义的是控制台response的码。前端会额外根据我们细节定义的A00005进行说明展示
}
