package com.hxgz.config;

import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class AopTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 切入点
     * @author:SimpleWu
     * @Date:2018年10月12日
     */
    @Pointcut(value="execution(* com.hxgz.implement..*.*(..))")
    public void aop(){}

    /**
     * 前置通知
     * @author:SimpleWu
     * @Date:2018年10月12日
     */
    @Before("aop()")
    public void aopBefore(JoinPoint joinPoint){
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("前置通知 SpringBootAspect....aopBefore");

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * 后置返回通知
     * @AfterReturning注解用于获取方法的返回值
     * @param object
     */
    @AfterReturning(pointcut = "aop()", returning = "object")
    public void getAfterReturn(Object object) {
        logger.info("aop afterReturning返回值:{}", JSONObject.fromObject(object));
    }
    /**
     * 后置最终通知
     * @author:SimpleWu
     * @Date:2018年10月12日
     */
    @After("aop()")
    public void aopAfter(){
        logger.info("后置通知  SpringBootAspect....aopAfter");
    }
    /**
     * 处理未处理的JAVA异常
     * @author:SimpleWu
     * @Date:2018年10月12日
     */
    @AfterThrowing(pointcut="aop()",throwing="e")
    public void exception(Exception e){
        logger.info("异常通知 SpringBootAspect...exception .."+e);
    }

//    /**
//     * 环绕通知
//     * @author:SimpleWu
//     * @throws Throwable
//     * @Date:2018年10月12日
//     */
//    @Around("aop()")
//    public void around(ProceedingJoinPoint invocation) throws Throwable{
//        logger.info("SpringBootAspect..环绕通知 Before");
//        invocation.proceed();
//        logger.info("SpringBootAspect..环绕通知 After");
//    }
}
