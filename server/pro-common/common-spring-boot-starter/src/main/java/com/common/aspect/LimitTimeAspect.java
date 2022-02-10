package com.common.aspect;


import com.common.aspect.annotation.LimitTime;
import com.common.domain.constants.LimitTimeTypeEnum;
import com.common.util.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @auth guozhenhua
 * @date 2018/11/05
 */

@Aspect
@Component
public class LimitTimeAspect extends BaseAspect{


    private Map<Method, Semaphore> semaphoreCache = new ConcurrentHashMap<>();

    private ExecutorService executorService = Executors.newFixedThreadPool(9);

    @Pointcut("@annotation(com.common.aspect.annotation.LimitTime)")
    public void aroundLimitTime() {
    }

    @Around("aroundLimitTime()")
    public Object advice(ProceedingJoinPoint joinPoint)throws Throwable {
        long startTime = System.currentTimeMillis();
        StringBuilder stringBuffer = new StringBuilder();
        Method method = getMethod(joinPoint);
        stringBuffer.append("请求方法名称:").append(method.getName()).append(",");
        stringBuffer.append(getParamsStr(joinPoint, method));
        //处理限制策略
        if (method.getAnnotation(LimitTime.class).type()==LimitTimeTypeEnum.NULL){
            Object proceed = joinPoint.proceed(joinPoint.getArgs());
            LogUtil.info(stringBuffer.append("请求耗时:").append(System.currentTimeMillis() - startTime).append("耗秒.").toString());
            return proceed;
        }

        Object returnObject;
        if (method.getAnnotation(LimitTime.class).type()== LimitTimeTypeEnum.LIMIT){
            returnObject=limit(joinPoint);
        }else {
            returnObject=timeout(joinPoint);
        }
        LogUtil.info(stringBuffer.append("请求耗时:" + (System.currentTimeMillis() - startTime) + "耗秒.").toString());
        return returnObject;
    }

    /**
     * 超时限制
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private Object timeout(ProceedingJoinPoint joinPoint)throws Exception{
        Object returnValue = null;
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            LimitTime timeout = method.getAnnotation(LimitTime.class);
            if (timeout != null) {
                long value = timeout.timeValues();
                TimeUnit timeUnit = timeout.timeUnit();
                String fallbackMethodName = timeout.fallback();
                Object[] arguments = joinPoint.getArgs();
                Future<Object> future = executorService.submit(() -> {
                    try {
                        return joinPoint.proceed(arguments);
                    } catch (Throwable throwable) {
                        throw new Exception(throwable);
                    }
                });
                try {
                    returnValue = future.get(value, timeUnit);
                } catch (TimeoutException e) {
                    returnValue = invokeFallbackMethod(method, joinPoint.getTarget(), fallbackMethodName, arguments);      // 补偿处理
                }

            }
        }
        return returnValue;
    }

    private Object invokeFallbackMethod(Method method, Object bean, String fallback, Object[] arguments) throws Exception {
        Class beanClass = bean.getClass();
        Method fallbackMethod = beanClass.getMethod(fallback, method.getParameterTypes());
        return fallbackMethod.invoke(bean, arguments);
    }


    /**
     * 处理信号量限制
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
            Semaphore semaphore = getSemaphore(method);
            try {
                semaphore.acquire();
                returnValue = joinPoint.proceed();
            } finally {
                semaphore.release();
            }
        }
        return returnValue;
    }

    /**
     * 获取信号量
     * @param method
     * @return
     */
    public Semaphore getSemaphore(Method method) {
        return semaphoreCache.computeIfAbsent(method, k -> {
            LimitTime limited = method.getAnnotation(LimitTime.class);
            int permits = limited.limitValue();
            return new Semaphore(permits);
        });
    }





}
