package com.gitee.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志切面
 *<P>
 *     1.统计业务方法调用时长
 *</P>
 * @author daiwei
 * @version 1.0.0
 * @date 16/5/17 上午10:42.
 */
@Aspect //使用@Aspect注解将一个java类定义为切面类
@Order(5)
@Component
public class LogAspect {

    private Logger logger = Logger.getLogger(getClass());

    //ThreadLocal 提供了线程局部 (thread-local) 变量，希望将状态与某一个线程（例如，用户 ID 或事务 ID）相关联。

    ThreadLocal<Long> startTime = new ThreadLocal<>();


    /**
     * 使用@Pointcut定义一个切入点，可以是一个规则表达式，
     * 比如下例中某个package下的所有函数，也可以是一个注解等。
     */
    @Pointcut("execution(public * com.gitee.service..*.*(..))")
    public void serviceLog(){}



    @Before("serviceLog()")
    public void serviceBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //获取请求参数
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }


    @AfterReturning(returning = "ret", pointcut = "serviceLog()")
    public void serviceAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }


}

