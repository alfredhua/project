package com.auth.service;

import com.auth.constants.authData.AuthDataErrorEnum;
import com.auth.dao.AuthDataMapper;
import com.auth.entity.AuthData;
import com.common.api.constants.SysErrorCodeEnum;
import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.api.entity.response.ResultResponse;
import com.common.api.exception.ResultException;
import com.common.mybatis.entity.EntityWrapper;
import com.common.mybatis.enums.ConditionEnum;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author guozhenhua
 * @date 2021/05/23
 */

@Component
public class AuthDataService {

    @Autowired
    AuthDataMapper dataMapper;

    public PageBean<AuthData> listAuthDataByPage(PageRequest pageRequest) {
        PageBean<AuthData> pageBean = PageUtil.getPageBean(pageRequest.getPage_num(),pageRequest.getPage_size(),pageRequest.getOffset());
        pageBean.setList(dataMapper.listByPage(pageBean.getPage_num(), pageBean.getPage_size(),null));
        pageBean.setTotal(dataMapper.listCount(null));
        return pageBean;
    }

    public void saveAuthData(AuthData authData) throws ResultException {
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.addCondition("code", ConditionEnum.eq,authData.getCode());
        List<AuthData> list = dataMapper.listAll(entityWrapper);
        if (list!=null && !list.isEmpty()){
            throw ResultException.error(AuthDataErrorEnum.CODE_EXIST.getCode(),AuthDataErrorEnum.CODE_EXIST.getMsg());
        }
        if (ObjectUtils.isEmpty(authData.getId())) {
            authData.setId(IDGenerateUtil.generateId());
            dataMapper.insert(authData);
            return;
        }
        dataMapper.updateById(authData);
    }

    public List<AuthData> listAuthDataAll() {
        return dataMapper.listAll(null);
    }
}
