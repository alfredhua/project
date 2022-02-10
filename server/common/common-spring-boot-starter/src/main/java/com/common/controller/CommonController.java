package com.common.controller;

import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.response.JSONResult;
import com.common.util.GsonUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Map;

@RestController
public class CommonController {

    @RequestMapping(value="/api/{clazz}/{method}",method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
    public JSONResult request(@PathVariable("clazz") String clazz, @PathVariable("method") String methodStr,
                              @RequestBody(required = false) Map<String, Object> param){
        try {
            Object bean = SpringUtil.getBean(clazz);
            Method[] methods = bean.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodStr)){
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Object invoke;
                    if (parameterTypes.length>0){
                        invoke= method.invoke(bean, GsonUtil.toClass(param, parameterTypes[0]));
                    }else {
                        invoke = method.invoke(bean);
                    }
                    if (invoke instanceof JSONResult){
                        return (JSONResult) invoke;
                    }
                    return JSONResult.success(invoke);
                }
            }
            return JSONResult.errorSysError(SysErrorCodeEnum.METHOD_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorSysError(SysErrorCodeEnum.ERR_SERVICE_STOP);
        }
    }

}
