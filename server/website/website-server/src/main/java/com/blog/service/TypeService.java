package com.blog.service;

import com.blog.dao.TypeMapper;
import com.blog.dto.TypeListReqDTO;
import com.blog.dto.entity.Type;
import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.JSONResult;
import com.common.domain.response.PageBean;
import com.common.util.IDGenerate;
import com.common.util.PageUtil;
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
        typeReqDTO.setId(IDGenerate.generateId());
        typeReqDTO.setCreate_time(LocalDateTime.now());
        typeMapper.createType(typeReqDTO);
    }

     
     public Type getById(long id) {
         return typeMapper.getById(id);
     }

    
    public JSONResult updateType(Type typeReqDTO) {
        if(typeMapper.updateType(typeReqDTO)){
            return JSONResult.success();
        }else{
            return JSONResult.error(SysErrorCodeEnum.UPDATE_ERROR.getCode(), SysErrorCodeEnum.UPDATE_ERROR.getMsg());
        }
    }

    
    public void delType(long id) throws Exception {
        if(typeMapper.delType(id)){
            return ;
        }
        throw ResultException.error(SysErrorCodeEnum.DEL_ERROR);
    }

    
    public PageBean<Type> listTypeByPage(TypeListReqDTO typeListReqDTO) {
        PageBean<Type> pageBean = PageUtil.validatePage(typeListReqDTO.getPage_num(),
                typeListReqDTO.getPage_size(),typeListReqDTO.getOffset());
        pageBean.setList(typeMapper.listTypeByPage(typeListReqDTO));
        pageBean.setTotal(typeMapper.listTypeCount(typeListReqDTO));
        return pageBean;
    }

    
    public List<Type> listAll() {
        return typeMapper.listAll();
    }

    
    public List<Type> listAllActive() {
        return typeMapper.listAllActive();

    }

    public void updateTypeStatus(long id, int status) throws Exception {
        if (typeMapper.updateTypeStatus(id,status)){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);

    }

    public Type getByType(String type) {
        return typeMapper.getByType(type);
    }
}
