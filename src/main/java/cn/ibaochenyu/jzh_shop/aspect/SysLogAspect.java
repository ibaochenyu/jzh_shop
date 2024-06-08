package cn.ibaochenyu.jzh_shop.aspect;

import cn.ibaochenyu.jzh_shop.annotation.SysLogMyAnnotation;
import cn.ibaochenyu.jzh_shop.dao.entity.SysLogDO;
import cn.ibaochenyu.jzh_shop.service.SysLogService;
import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
//Autowired members must be defined in valid Spring bean (@Component|@Service|...)
//因此这里得加@Component
@Component
@Slf4j
//SLF4J，即简单日志门面（Simple Logging Facade for Java），不是具体的日志解决方案，
//Log4j是Apache的一个开源项目，通过使用Log4j，我们可以控制日志信息输送的目的地是控制台、文件、GUI组件，甚至是套接口服务器、NT的事件记录器、UNIX Syslog守护进程等；
//mall4j用的是log4j
public class SysLogAspect {

    @Autowired//貌似是返回已经有的一个sysLogService就行
    private SysLogService sysLogService;

//    Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Around("@annotation(sysLogMyAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint, SysLogMyAnnotation sysLogMyAnnotation) throws Throwable {
        long beginTime = SystemClock.now();

        Object result = joinPoint.proceed();

        long endTime = SystemClock.now();

        long duration=endTime-beginTime;
        ////
        SysLogDO tableOneElement =new SysLogDO();
        tableOneElement.setDate(new Date());//返回现在的date，生成一个咯
        tableOneElement.setDuration(duration);

        if(sysLogMyAnnotation!=null){
            tableOneElement.setComment(sysLogMyAnnotation.mvalue());
        }

        tableOneElement.setIp("0.0.0.0");
//        # 返回目标对象，即被代理的对象
//        Object getTarget();
//
//# 返回切入点的参数
//        Object[] getArgs();
//
//# 返回切入点的 Signature      [['signәtʃә]n. 签字, 识别标志, 调号]
//        Signature getSignature();


        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        tableOneElement.setOperatorClass(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params;
        try {
            params = new ObjectMapper().writeValueAsString(args[0]);
        }catch(Exception e){
            System.out.println("对象转jsonn错误");
            params="sth error";
        }
        tableOneElement.setOperatorArgv(params);

        sysLogService.save(tableOneElement);

        log.info("执行around完毕。当时函数：{}",methodName);//slf4j后，是log.info,不是logger
//        log.error("执行around完毕。当时函数：{}",methodName);//slf4j后，是log.info,不是logger
//
        return result;

    }
}
