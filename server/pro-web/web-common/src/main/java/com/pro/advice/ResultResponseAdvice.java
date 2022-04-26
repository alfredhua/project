package com.pro.advice;

import com.common.api.entity.response.ResultResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 返回统一的 JSONResult 格式
 */
@ControllerAdvice(basePackages = {"com.pro.controller"})
public class ResultResponseAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(MediaType.APPLICATION_JSON.equals(selectedContentType)){
            if(body instanceof ResultResponse){
                return body;
            }else{
                return ResultResponse.success(body);
            }
        }
        throw new RuntimeException("request context type is not application/json");
    }

}