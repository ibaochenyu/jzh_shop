package cn.ibaochenyu.jzh_shop;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice//务必加这个注释，否则无法拦截
public class GlobalExceptionHandleMyself {

    @ExceptionHandler(value = Throwable.class)
    public ServerResponseEntity defaultErrorHandle(HttpServletRequest request,Throwable throwable) {
        log.error("进行defaultErrorHandle处理：[{}] {} ", request.getMethod(), getUrl(request),throwable);//我测试了下，写throwable会打印错误消息+堆栈。
        return ServerResponseEntity.fail();
    }



    //@ExceptionHandler(value = java.sql.SQLIntegrityConstraintViolationException.class)//从控制台拿来的。这么做不行。https://blog.csdn.net/u011623836/article/details/110392373
    @ExceptionHandler(value = DataAccessException.class)
    public ServerResponseEntity sqlExceptionHandle(HttpServletRequest request,Throwable throwable){

        //如果你没有第三个{},应该写throwable，而不是throwable.getMessage()
        //log.error("进行Throwable处理：[{}] {} ", request.getMethod(), getUrl(request),throwable);//我测试了下，写throwable会打印错误消息+堆栈。
//        log.error("//////111/////");
//        log.error("测试参数：{}",throwable.getMessage());//此处getMessage只是打印错误信息，并占用{}

        log.error("sql执行错误。进行sqlExceptionHandle：[{}] {} ", request.getMethod(), getUrl(request));//我测试了下，写throwable会打印错误消息+堆栈。
        log.error("测试参数：{}",throwable.getMessage());

        //log.error("进入GlobalExceptionHandleMyself关于Throwable的处理：{}",e.getMessage());
        return ServerResponseEntity.fail(ResponseEnum.SQL_DUPLICATE);

        //我看过了，铁路注册的用户名如果重复，首部状态码是200，responce的错误码才是自己细节定义的
    }//此处定义的是控制台response的码。前端会额外根据我们细节定义的A00005进行说明展示

    private String getUrl(HttpServletRequest request) {
        if (StringUtils.isEmpty(request.getQueryString())) {
            return request.getRequestURL().toString();
        }
        else
            return "-1";
    }
}
