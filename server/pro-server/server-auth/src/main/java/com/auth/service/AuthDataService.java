package com.auth.service;

import com.auth.dao.AuthDataMapper;
import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import com.pro.auth.dto.AuthDataReqDTO;
import com.pro.auth.dto.entity.AuthData;
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

    public void saveAuthData(AuthData authData){
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
