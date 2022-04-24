package com.pro.advice;

import com.common.api.constants.SysErrorCodeEnum;
import com.common.api.entity.response.ResultResponse;
import com.common.api.exception.ResultException;
import com.common.util.EnvUtil;
import com.common.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.currentThread;

/**
 * RuntimeException 全局异常处理
 */
@Slf4j
@ControllerAdvice(basePackages = "com.pro.controller")
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return objectError.getDefaultMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultResponse exceptionHandler(HttpServletRequest httpServletRequest, Exception ex) {
        if (ex instanceof ResultException){
            ResultException resultException=(ResultException) ex;
            return ResultResponse.error(resultException.getCode(),resultException.getMsg());
        }
        StringBuilder errorData = getErrorData(ex);
        log.error(errorData.toString());
        String requestURI = httpServletRequest.getRequestURI();
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        String paramsStr="";
        if (parameterMap!=null){
            paramsStr= GsonUtil.toJSONString(parameterMap);
        }
        errorData.append("请求地址:<br>").append(requestURI).append("<br> 请求参数:<br>" ).append(paramsStr).append("<br>");
        List<String> toMailList=new ArrayList<>();
//        toMailList.add(mailProperties.getTo_mail());
        if(!EnvUtil.isDevActive()) {
            try {
//                MailCle.sendMails(toMailList, "错误故障", errorData.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResultResponse.error(SysErrorCodeEnum.ERR_SYSTEM.getCode(),SysErrorCodeEnum.ERR_SYSTEM.getMsg());
    }

    private StringBuilder getErrorData(Throwable ex){
        StringBuilder error=new StringBuilder();
        OutputStream ops=new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(ops));
        error.append(ops)
                .append("<br>线程号:")
                .append(currentThread().getId())
                .append("，线程名:")
                .append(currentThread().getName())
                .append("<br>");
        return error;
    }

}
