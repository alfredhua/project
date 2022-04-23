package com.pro.controller.common;

import com.common.api.entity.response.PageBean;
import com.common.util.BeanCopyUtil;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class BaseController {

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
