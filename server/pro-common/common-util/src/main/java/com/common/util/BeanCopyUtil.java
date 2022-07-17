package com.common.util;

import com.common.api.entity.response.PageBean;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean 转换器 VO-->DTO DTO-->VO
 * @author hua
 */
public class BeanCopyUtil {

    public static<T,M> T copy(M source,Class<T> des){
        T t = null;
        try {
            t = des.newInstance();
            BeanUtils.copyProperties(source,t);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
        return t;
    }

    public static <T,M> PageBean<T> copyPageBean(PageBean<M> mPageBean, Class<T> t){
        PageBean<T> tPageBean=new PageBean<>();
        List<T> tList = new ArrayList<>();
        try {
            for (M m :mPageBean.getList()) {
                T newT = t.newInstance();
                BeanUtils.copyProperties(m, newT);
                tList.add(newT);
            }
            tPageBean.setList(tList);
            tPageBean.setOffset(mPageBean.getOffset());
            tPageBean.setPage_num(mPageBean.getPage_num());
            tPageBean.setPage_size(mPageBean.getPage_size());
            tPageBean.setTotal(mPageBean.getTotal());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tPageBean;
    }

    public static<T,M> List<T> copyList(List<M> list,Class<T> des){
        List<T> newList=new ArrayList<>();
        try {
            for (M m : list) {
                T t = des.newInstance();
                BeanUtils.copyProperties(m,t);
                newList.add(t);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return newList;
    }


}
