package com.common.util;


import com.common.api.entity.response.PageBean;

/**
 * @author hua
 */
public class PageUtil {

    public static<T> PageBean<T> getPageBean(Integer pageNum, Integer pageSize, Integer offset){
        PageBean<T> pageResult=new PageBean<>();
        pageResult.setPage_num(pageNum);
        if(pageSize!=null){
            pageResult.setPage_size(pageSize);
        }
        pageResult.setOffset(offset);
        return pageResult;
    }
}
