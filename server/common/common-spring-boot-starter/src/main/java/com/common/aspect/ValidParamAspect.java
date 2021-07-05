package com.common.aspect;

import com.common.domain.exception.ResultException;
import com.common.domain.response.JSONResult;
import com.common.util.BeanCopyUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Collect层中的参数验证切面
 * 配合BindingResult和 @Valid 一起使用
 */
//@Aspect
//@Component
//@Order(1)
//@Slf4j
public class ValidParamAspect extends BaseAspect {

    @Pointcut("execution (* com.admin.controller..*.*(..)) || execution (* com.site.controller..*.*(..)) ")
    public void aroundValidParam() {
    }

    @Around("aroundValidParam()")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] arguments = joinPoint.getArgs();
        for (Object o:arguments) {
            if ( o instanceof BindingResult){
                BindingResult result=(BindingResult)o;
                if (result.hasErrors()) {
                    FieldError fieldError = result.getFieldError();
                    throw new ResultException(fieldError.getField(), fieldError.getDefaultMessage());
                }
            }
        }
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        }catch (Throwable e){
//            log.error("异常啊啊啊",e);
        }
        return null;
    }


    /**
     * 具有data的参数的返回，获取数据
     * @param result
     * @param t
     * @param <T>
     * @return
     */
    @SuppressWarnings(value ="unchecked")
    protected static<T> JSONResult<T> resultReturn(JSONResult result,Class<T> t){
        if (!JSONResult.SUCCESS.equals(result.getCode())&&t!=null){
            return JSONResult.error(result.getCode(), result.getMsg());
        }
        if (ObjectUtils.isEmpty(result.getData())){
            return JSONResult.success(null);
        }
        return JSONResult.success(BeanCopyUtil.copy(result.getData(), t));
    }

    /**
     * 无参数返回，成功失败
     * @param result
     * @param <T>
     * @return
     */
    protected static<T> JSONResult<T> resultReturn(JSONResult result){
        if(!JSONResult.SUCCESS.equals(result.getCode())){
            return JSONResult.error(result.getCode(), result.getMsg());
        }
        return JSONResult.success();
    }

    /**
     * 返回list数据
     * @param result
     * @param tClass
     * @param <T>
     * @param <M>
     * @return
     */
    protected static<T,M> JSONResult<List<M>> listReturn(JSONResult<List<T>> result, Class<M> tClass) {
        if(!JSONResult.SUCCESS.equals(result.getCode())){
            return JSONResult.error(result.getCode(), result.getMsg());
        }
        if (ObjectUtils.isEmpty(result.getData())){
            return JSONResult.success();
        }
        return JSONResult.success(BeanCopyUtil.copyList(result.getData(), tClass));
    }
}
