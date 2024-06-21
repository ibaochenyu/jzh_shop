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
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
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


    //annotation     [ænәu'teiʃәn]n. 注解, 评注
    //@Around("@annotation(自定义注解)")//自定义注解标注在方法上的方法执行aop方法
    //@Around("@within(自定义注解)")//自定义注解标注在的类上；该类的所有方法（不包含子类方法）执行aop方法


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



        //Method 类属于 Java 标准反射 API，位于 java.lang.reflect 包下。主要用于在运行时获取和操作类的方法，支持动态调用。
        //用于获取方法的详细信息，如方法名称、返回类型、参数类型等。

        //MethodSignature(Signature ['signәtʃә]n. 签字, 识别标志)
        //MethodSignature 属于 Spring AOP 框架，位于 org.aspectj.lang.reflect 包下。主要用于 Spring AOP 中，用于在切面中获取被代理方法的签名信息，方便切面进行条件匹配、日志记录等操作。
        //通过 JoinPoint 对象获取 MethodSignature 对象。用于获取切点方法的详细信息，如方法名称、返回类型、参数类型等。

        //Signature和MethodSignature
        // Signature 用于描述任何程序元素（方法、构造函数或字段），而 MethodSignature 则专门用于描述方法。


        //【核心】
        //获取Class                    joinPoint.getTarget().getClass()
        //获取MethodSignature          joinPoint.getSignature()
        //获取Method                   joinPoint.getSignature().getMethod()
        //获取注解内容                  joinPoint.getSignature().getMethod().getAnnotation(IdempotentX.class)
        //获取切入点方法的形参           JoinPoint.getArgs()【无法使用joinPoint.getSignature().getMethod()方案】

        //铁路：
        //获取Class          joinPoint.getTarget().getClass()
        //获得Class名        joinPoint.getTarget().getClass().getName
        //获得整个声明的方法  c=joinPoint.getTarget().getClass().getDeclaredMethod(a,b)
        //获得注解内容       c.getAnnotation(IdempotentX.class)

        //获取MethodSignature    joinPoint.getSignature()
        //获取方法名              a=joinPoint.getSignature().getName()
        //获取方法的种类          b=joinPoint.getSignature().getMethod().getParameterTypes()

////////////////////////////////////////////////

        Class<?> mClassOri=joinPoint.getClass();//joinPoint.getClass() 返回的是 JoinPoint 对象本身的类对象
        Class<?> mClass=joinPoint.getTarget().getClass();//joinPoint.getTarget().getClass() 返回的是被代理对象的实际类对象
        String mClassName=joinPoint.getTarget().getClass().getName();//cn.ibaochenyu.jzh_shop.controller.ProduceController

        MethodSignature mMethodSignature=(MethodSignature)joinPoint.getSignature();
        String mMethodSignatureName_A=((MethodSignature)joinPoint.getSignature()).getName();//getOneProduceRtResult
        Class<?>[] mMethodSignatureClass_B=((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();//获得一个数组，成员有java.util.Date  ...  java.lang.Long  ...

        Method mMethod_C=joinPoint.getTarget().getClass().getDeclaredMethod(mMethodSignatureName_A,mMethodSignatureClass_B);
        SysLogMyAnnotation rt=mMethod_C.getAnnotation(SysLogMyAnnotation.class);//获得注解具体内容

        Object[] mArgs=joinPoint.getArgs();

        //我尝试了，((MethodSignature)joinPoint.getSignature()).getMethod()和mMethod_C其实是一个内容啊
        //一个是从Signature取
        //一个是从Class根据函数名字和函数参数取

        Method[] methods = mClass.getDeclaredMethods();//获得了六个类：class下的具体类。每个成员都是Method方法
        for (Method method : methods) {
            // 打印方法名称
            System.out.println("Method name: " + method.getName());//返回update

            // 打印参数类型
            Class<?>[] parameterTypes = method.getParameterTypes();//返回一个数组，由于我填的参数只有一个，数组[0]=Class=class cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO
            for (Class<?> paramType : parameterTypes) {
                System.out.println("Parameter type: " + paramType.getName());//返回String类型：cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO
            }

            // 打印返回类型
            System.out.println("Return type: " + method.getReturnType().getName());//返沪cn.ibaochenyu.jzh_shop.myResponse.ServerResponseEntity
        }

////////////////////////////////////////////////

//("exampleMethod", String.class, int.class);





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
//设置锁
//set a b 设置a的值为b
//set a b nx   仅当a不存在时，设置a的值为b 。a不存在返回1，a存在返回0
//set a b ex time nx   添加了额外过期参数，time秒过期    【EX second ：设置键的过期时间为 second 秒。】

//redisTemplate.opsForValue().setIfAbsent(key,value,timeout,timeUnit)
//stringRedisTemplate.opsForValue().set(key, actual, timeout, timeUnit);
//stringRedisTemplate.opsForValue().setIfAbsent(key,actual,timeout,timeUnit);
