package com.common.aspect;

import com.common.util.GsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guozhenhua
 * @date 2021/05/25
 */
public abstract class BaseAspect {



     public Method getMethod(ProceedingJoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }

    /**
     * 获取请求参数
     * @param joinPoint
     * @param method
     * @return
     */
     public String getParamsStr(ProceedingJoinPoint joinPoint,Method method){
        StringBuilder stringBuffer=new StringBuilder();
        List<Object> args = filter(joinPoint.getArgs(), method);
        if (args.size() > 0) {
            for (Object arg : args) {
                stringBuffer.append("请求参数:").append(GsonUtil.toJSON(arg)).append(",");
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 请求参数过滤
     * @param args
     * @param targetMethod
     * @return
     */
     private List<Object> filter(Object[] args, Method targetMethod) {
        Annotation[][] annotationList = targetMethod.getParameterAnnotations();
        List<Object> list=new ArrayList<>();
        for (int i = 0; i < annotationList.length; i++) {
            for (int j = 0; j < annotationList[i].length; j++) {
                if (annotationList[i][j].annotationType() == RequestBody.class||
                        annotationList[i][j].annotationType() == PathVariable.class) {
                    list.add(args[i]);
                }
            }
        }
        return list;
    }
}
