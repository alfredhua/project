package com.auth.service;

import com.auth.dao.AuthDataMapper;
import com.auth.dto.AuthDataReqDTO;
import com.auth.dto.entity.AuthData;
import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
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

    public PageBean<AuthData> listAuthDataByPage(AuthDataReqDTO authDataReqDTO) {
        PageBean<AuthData> pageBean = PageUtil.validatePage(authDataReqDTO.getPage_num(),
                authDataReqDTO.getPage_size(),authDataReqDTO.getOffset());
        pageBean.setList(dataMapper.listAuthDataByPage(pageBean.getOffset(), pageBean.getPage_size()));
        pageBean.setTotal(dataMapper.listAuthDataCount());
        return pageBean;
    }

    public void saveAuthData(AuthData authData) throws Exception{
        try {
            if (ObjectUtils.isEmpty(authData.getId())) {
                authData.setId(IDGenerateUtil.generateId());
                dataMapper.saveAuthData(authData);
                return;
            }
            dataMapper.updateAuthData(authData);
        }catch (Exception e){
            throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
        }
    }

    public List<AuthData> listAuthDataAll() {
        return dataMapper.listAuthDataAll();
    }
}
