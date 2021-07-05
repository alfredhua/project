package com.admin.controller.common;

import com.auth.dto.LoginAdminRespDTO;
import com.common.domain.response.PageBean;
import com.common.redis.RedisUtils;
import com.common.util.BeanCopyUtil;
import com.admin.constants.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AdminBaseController {
    @Autowired
    RedisUtils redisUtils;


    protected String getToken(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return  request.getHeader("token");
    }

    protected LoginAdminRespDTO getLoginAdminInfo(){
        LoginAdminRespDTO loginAdminRespDTO = redisUtils.objectGet(CommonConstant.ADMIN_INFO.getKey() + getToken());
        if (ObjectUtils.isEmpty(loginAdminRespDTO)){
            throw new RuntimeException("用户登录失效:"+ getToken());
        }
        return loginAdminRespDTO;
    }

    protected static<T,E> PageBean<T> pageResultReturn(PageBean<E> pageBean, Class<T> t){
        if (pageBean.getList()==null||pageBean.getList().isEmpty()){
            PageBean<T> returnPageBean=new PageBean<>();
            pageBean.setTotal(0);
            pageBean.setPage_num(returnPageBean.getPage_num());
            return returnPageBean;
        }
        return BeanCopyUtil.copyPageBean(pageBean, t);
    }

    protected static<M,T> T resultReturn(M result,Class<T> t){
        return  BeanCopyUtil.copy(result, t);
    }

    protected static<T,M> List<M> listReturn(List<T> result, Class<M> tClass) {
        if (ObjectUtils.isEmpty(result)){
            return new ArrayList<>();
        }
        return BeanCopyUtil.copyList(result, tClass);
    }


}
