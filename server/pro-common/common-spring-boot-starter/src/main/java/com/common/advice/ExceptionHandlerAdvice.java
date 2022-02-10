package com.common.advice;

import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.JSONResult;
import com.common.middle.mail.MailConfigProperties;
import com.common.middle.mail.MailUtils;
import com.common.util.EnvUtils;
import com.common.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @Autowired
    MailConfigProperties mailProperties;


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JSONResult exceptionHandler(HttpServletRequest httpServletRequest, Exception ex) {
        if (ex instanceof ResultException){
            ResultException resultException=(ResultException) ex;
            return JSONResult.error(resultException.getCode(),resultException.getMsg());
        }
        ex.printStackTrace();

        StringBuilder errorData = getErrorData(ex);
        String requestURI = httpServletRequest.getRequestURI();
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        String paramsStr="";
        if (parameterMap!=null){
            paramsStr= GsonUtils.toJSONString(parameterMap);
        }
        errorData.append("请求地址:<br>").append(requestURI).append("<br> 请求参数:<br>" ).append(paramsStr).append("<br>");
        List<String> toMailList=new ArrayList<>();
        toMailList.add(mailProperties.getTo_mail());
        if(!EnvUtils.isDevActive()) {
            try {
                MailUtils.sendMails(toMailList, "错误故障", errorData.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JSONResult.error(SysErrorCodeEnum.ERR_SYSTEM.getCode(),SysErrorCodeEnum.ERR_SYSTEM.getMsg());
    }

    private StringBuilder getErrorData(Throwable ex){
        StringBuilder error=new StringBuilder();
        OutputStream ops=new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(ops));
        error.append(ops.toString())
                .append("<br>线程号:")
                .append(currentThread().getId())
                .append("，线程名:")
                .append(currentThread().getName())
                .append("<br>");
        return error;
    }

}
