package com.site.advice;

import com.common.domain.response.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 返回统一的 JSONResult 格式
 */
@ControllerAdvice(basePackages = "com.admin.web.controller")
@Slf4j
public class ResultResponseAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(MediaType.APPLICATION_JSON.equals(selectedContentType)){
            // 如果响应返回的对象为统一响应体，则直接返回body
            if(body instanceof JSONResult){
                return body;
            }else{
                return JSONResult.success(body);
            }
        }
        return null;
    }

}