package com.admin.filter;

import com.admin.config.NoLoginUrlConfig;
import com.auth.constants.AuthConstant;
import com.auth.dto.LoginAdminRespDTO;
import com.auth.service.LoginService;
import com.common.domain.constants.SourceEnum;
import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.entity.UserInfo;
import com.common.domain.response.JSONResult;
import com.common.middle.redis.RedisUtils;
import com.common.util.LoginUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

/**
 * 验证是否登录
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*", filterName = "adminLoginFilter")
public class AdminLoginFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AdminLoginFilter.class);
    private static final DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    LoginService loginService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestURI = request.getRequestURI();
        if (NoLoginUrlConfig.containURI(requestURI)){
            chain.doFilter(request,resp);
            return;
        }
        String token = request.getHeader("token");
        if (ObjectUtils.isEmpty(token)){
            returnResponse(response,JSONResult.errorSysError(SysErrorCodeEnum.TOKEN_IS_NULL));
            return;
        }
        LoginAdminRespDTO loginAdminRespDTO= RedisUtils.objectGet(AuthConstant.ADMIN_INFO.getKey() + token);
        if (ObjectUtils.isEmpty(loginAdminRespDTO)){
            returnResponse(response,JSONResult.errorSysError(SysErrorCodeEnum.TOKEN_INVALID));
            return;
        }
        UserInfo userInfo=new UserInfo();
        userInfo.setId(loginAdminRespDTO.getId());
        userInfo.setToken(token);
        userInfo.setEmail(loginAdminRespDTO.getEmail());
        userInfo.setUser_name(loginAdminRespDTO.getUser_name());
        userInfo.setPhone(loginAdminRespDTO.getPhone());
        userInfo.setCreate_time(loginAdminRespDTO.getCreate_time());
        userInfo.setSourceEnum(SourceEnum.ADMIN);
        LoginUtils.initLoginUser(userInfo);
        RedisUtils.expire(AuthConstant.ADMIN_INFO.getKey()+token, AuthConstant.ADMIN_INFO.getTimeOut()); //延迟登录时间
        chain.doFilter(request,resp);
        LoginUtils.remove();
    }

    private void returnResponse(HttpServletResponse response ,JSONResult jsonResult){
        try {
            response.setHeader("Content-Type", "application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(200);
            response.getWriter().append(jsonResult.toString());
        }catch (Exception e){
            logger.error("AdminLoginFilter error",e);
        }
    }


    private void logParameter(HttpServletRequest request){
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuffer stringBuffer = new StringBuffer();
        while (parameterNames.hasMoreElements()){
            stringBuffer.append(request.getParameter(parameterNames.nextElement()+","));
        }
    }

}
