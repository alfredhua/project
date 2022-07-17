package com.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * @author hua
 * @date 2022/7/17
 */
public class LogAspect extends BaseAspect{

    @Pointcut("@annotation(com.common.aspect.annotation.Log)")
    public void aroundLog() {
    }

    @Around("aroundLog()")
    public Object advice(ProceedingJoinPoint joinPoint)throws Throwable {
        StopWatch stopWatch = new StopWatch();
        Method method = getMethod(joinPoint);
        return null;
    }

}
