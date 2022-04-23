package com.blog.service;

import com.blog.dao.TypeMapper;
import com.common.api.entity.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import com.pro.blog.dto.TypeListReqDTO;
import com.pro.blog.dto.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
@Component
public class TypeService {

    @Autowired
    TypeMapper typeMapper;


    public void createType(Type typeReqDTO) {
        typeReqDTO.setId(IDGenerateUtil.generateId());
        typeReqDTO.setCreate_time(LocalDateTime.now());
        typeMapper.insert(typeReqDTO);
    }


    public Type getById(long id) {
        return typeMapper.queryById(id);
    }

    public boolean updateType(Type typeReqDTO) {
        return typeMapper.updateById(typeReqDTO);
    }


    public boolean delType(long id) {
        return typeMapper.deleteById(id);
    }


    public PageBean<Type> listTypeByPage(TypeListReqDTO typeListReqDTO) {
        PageBean<Type> pageBean = PageUtil.getPageBean(typeListReqDTO.getPage_num(),typeListReqDTO.getPage_size(),typeListReqDTO.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        pageBean.setList(typeMapper.listByPage(pageBean.getPage_num(),pageBean.getPage_size(),entityWrapper));
        pageBean.setTotal(typeMapper.listCount(entityWrapper));
        return pageBean;
    }


    public List<Type> listAll() {
        EntityWrapper entityWrapper=new EntityWrapper();
        return typeMapper.listAll(entityWrapper);
    }


    public List<Type> listAllActive() {
        EntityWrapper entityWrapper=new EntityWrapper();
        return typeMapper.listAll(entityWrapper);
    }

    public boolean updateTypeStatus(long id, int status){
        Type type=new Type();
        type.setId(id);
        type.setStatus((short)status);
        return typeMapper.updateById(type);
    }

    public Type getByType(String type) {
        return typeMapper.getByType(type);
    }
}
