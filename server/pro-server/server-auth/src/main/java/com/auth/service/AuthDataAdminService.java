package com.auth.service;

import com.auth.dao.AuthDataAdminMapper;
import com.auth.entity.AuthDataAdmin;
import com.common.util.IDGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author guozhenhua
 * @date 2021/05/23
 *  数据权限
 */

@Component
public class AuthDataAdminService {


    @Autowired
    AuthDataAdminMapper authDataAdminMapper;

    public void save(List<String> authCodeList,Long adminId) {
        authDataAdminMapper.deleteByAdminId(adminId);
        for (String authCode : authCodeList) {
            AuthDataAdmin authDataAdmin = new AuthDataAdmin();
            authDataAdmin.setId(IDGenerateUtil.generateId());
            authDataAdmin.setAdmin_id(adminId);
            authDataAdmin.setData_code(authCode);
            authDataAdminMapper.insert(authDataAdmin);
        }
    }

    public List<String> listByAdminId(long adminId) {
        return authDataAdminMapper.listByAdminId(adminId);
    }
}
