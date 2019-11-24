package com.lzq.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * Aop前置通知
     * 后置通知  方法正常调用之后执行
     * 环绕通知  方法调用前后都执行
     * 异常通知  如果在方法调用过程中发生异常则调用
     * 最终通知  方法调用之后执行
     *
     * 第一： *表示方法返回类型
     * 2 包名  代表AOP监控的类所在包
     * 3 。。代表该包下及其子包下的所有类方法
     * 4 *代表类名
     * 5 *(..) *代表类中的方法名，（..）代表方法中的任何参数
     */
    @Around("execution(* com.lzq.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint){

        logger.info("======开始执行{}.{}=====",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());
        long begin = System.currentTimeMillis();
        Object result= null;
        try {
            result =  joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long takeTime = end - begin ;
        if (takeTime > 3000){
            logger.error("================执行结束耗时 {}毫秒============",takeTime);
        }else if (takeTime >2000){
            logger.warn("================执行结束耗时 {}毫秒============",takeTime);
        }else {
            logger.info("================执行结束耗时 {}毫秒============",takeTime);
        }
        return result;
    }


}
