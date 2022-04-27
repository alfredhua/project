package com.pro.admin.filter;

import com.common.api.constants.RedisConstant;
import com.common.api.constants.SysErrorCodeEnum;
import com.common.api.entity.LoginUserInfo;
import com.common.api.entity.response.ResultResponse;
import com.common.redis.client.RedisClient;
import com.common.util.LogUtil;
import com.common.util.LoginUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证是否登录
 */
@WebFilter(urlPatterns = {"/admin/*","/logout"})
public class AdminLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String token = request.getHeader("token");
        if (ObjectUtils.isEmpty(token)){
            returnResponse(response, ResultResponse.errorSysError(SysErrorCodeEnum.TOKEN_IS_NULL));
            return;
        }
        LoginUserInfo loginUserInfo= RedisClient.objectGet(RedisConstant.ADMIN_INFO.getKey() + token);
        if (ObjectUtils.isEmpty(loginUserInfo)){
            returnResponse(response,ResultResponse.errorSysError(SysErrorCodeEnum.TOKEN_INVALID));
            return;
        }
        LoginUtil.initLoginUser(loginUserInfo);
        RedisClient.expire(RedisConstant.ADMIN_INFO.getKey()+token, RedisConstant.ADMIN_INFO.getTimeOut()); //延迟登录时间
        chain.doFilter(request,resp);
        LoginUtil.remove();
    }

    private void returnResponse(HttpServletResponse response ,ResultResponse jsonResult){
        try {
            response.setHeader("Content-Type", "application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(200);
            response.getWriter().append(jsonResult.toString());
        }catch (Exception e){
            LogUtil.error("AdminLoginFilter error",e);
        }
    }


}