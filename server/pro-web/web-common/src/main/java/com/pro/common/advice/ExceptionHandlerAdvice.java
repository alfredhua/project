package com.pro.common.advice;

import com.common.api.constants.SysErrorCodeEnum;
import com.common.api.entity.response.ResultResponse;
import com.common.api.exception.ResultException;
import com.common.entity.MailEntity;
import com.common.util.EnvUtil;
import com.common.util.GsonUtil;
import com.common.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author hua
 */
@Slf4j
@ControllerAdvice(basePackages = {"com.pro.admin.controller","com.pro.site.controller"})
public class ExceptionHandlerAdvice {

    @Autowired(required = false)
    MailEntity mailEntity;
    /**
     * 处理 参数错误异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return objectError.getDefaultMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = ResultException.class)
    public ResultResponse<Object> resultException(HttpServletRequest httpServletRequest, Exception ex){
        ResultException resultException=(ResultException) ex;
        return ResultResponse.error(resultException.getCode(),resultException.getMsg());
    }

    /**
     * 全局异常处理
     * @param httpServletRequest
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultResponse exceptionHandler(HttpServletRequest httpServletRequest, Exception ex) {
        StringBuilder errorData = getErrorData(ex);
        log.error(errorData.toString());
        String uri = httpServletRequest.getRequestURI();
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        String paramsStr="";
        if (parameterMap!=null){
            paramsStr= GsonUtil.toJSONString(parameterMap);
        }
        errorData.append("请求地址:<br>").append(uri).append("<br> 请求参数:<br>" ).append(paramsStr).append("<br>");
        try {
            if(!EnvUtil.isDevActive()) {
                List<String> toMailList = new ArrayList<>();
                toMailList.add(mailEntity.getToMail());
                MailUtil.sendMails(toMailList, "错误故障", errorData.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
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
