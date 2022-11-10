package com.common.aspect;


import com.common.aspect.annotation.LimitTime;
import com.common.constants.LimitTimeTypeEnum;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hua
 * @date 2018/11/05
 */

@SuppressWarnings("UnstableApiUsage")
@Aspect
@Component
public class LimitTimeAspect extends BaseAspect{


    private final Map<Method, RateLimiter> limitCache = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.common.aspect.annotation.LimitTime)")
    public void aroundLimitTime() {
    }

    @Around("aroundLimitTime()")
    public Object advice(ProceedingJoinPoint joinPoint)throws Throwable {
        Method method = getMethod(joinPoint);
        //处理限制策略
        if (method.getAnnotation(LimitTime.class).type()== LimitTimeTypeEnum.COUNT_LIMIT){
            return limit(joinPoint);
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }

    /**
     * 令牌桶限制
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    private Object limit(ProceedingJoinPoint joinPoint)throws Throwable{
        Object returnValue = null;
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            RateLimiter limiter = getRateLimiter(method);
            limiter.acquire();
            returnValue = joinPoint.proceed();
        }
        return returnValue;
    }

    /**
     * 获取信号量
     * @param method
     * @return
     */
    public RateLimiter getRateLimiter(Method method) {
        return limitCache.computeIfAbsent(method, k -> {
            LimitTime limited = method.getAnnotation(LimitTime.class);
            return RateLimiter.create(limited.limitValue());
        });
    }





}
