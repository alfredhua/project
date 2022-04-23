package com.auth.service;

import com.auth.constants.admin.AdminStatusEnum;
import com.auth.dao.AdminMapper;
import com.auth.dao.LoginLogMapper;
import com.auth.dao.MenuRoleMapper;
import com.auth.entity.Admin;
import com.auth.entity.LoginLog;
import com.auth.entity.MenuRole;
import com.common.api.constants.RedisConstant;
import com.common.api.constants.SysErrorCodeEnum;
import com.common.api.entity.LoginUserInfo;
import com.common.api.exception.ResultException;
import com.common.redis.client.RedisClient;
import com.common.util.BeanCopyUtil;
import com.common.util.GsonUtil;
import com.common.util.IDGenerateUtil;
import com.common.util.MessageDigestUtil;
import com.google.gson.reflect.TypeToken;
import com.pro.api.auth.login.LoginReqDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hua
 */
@Component
public class LoginService  {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    LoginLogMapper loginLogMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;

    @Autowired
    AuthDataAdminService authDataAdminService;

    public LoginUserInfo login(LoginReqDTO loginReqDTO) throws Exception{
        Admin admin = adminMapper.getAdminByUserName(loginReqDTO.getUser_name());
        if (ObjectUtils.isEmpty(admin)) {
            admin = adminMapper.getAdminByPhone(loginReqDTO.getUser_name());
        }
        if (ObjectUtils.isEmpty(admin)) {
            throw ResultException.error(SysErrorCodeEnum.USER_NAME_ERROR);
        }
        if (!MessageDigestUtil.base64AndMD5(loginReqDTO.getPassword()).equals(admin.getPassword())) {
            throw ResultException.error(SysErrorCodeEnum.PASS_WORD_ERROR);
        }
        if ( admin.getStatus() == AdminStatusEnum.FROZEN.getCode()) {
            throw ResultException.error(SysErrorCodeEnum.FROZEN);
        }
        String token = insertLoginLog(loginReqDTO.getIpAddress(), admin);
        LoginUserInfo loginUserInfo = loginUser(admin);
        RedisClient.objectSet(RedisConstant.ADMIN_INFO.getKey() + token, RedisConstant.ADMIN_INFO.getTimeOut(), loginUserInfo);
        loginUserInfo.setToken(token);
        return loginUserInfo;
    }

    private LoginUserInfo loginUser(Admin admin){
        //获取所有的roleIds
        Set<Long> roleIds = GsonUtil.toSetLong(admin.getRole_id_list());
        List<MenuRole> authList = menuRoleMapper.listByIds(roleIds);
        //所有的authDictionary id的set
        Set<String> authSet = new HashSet<>();
        for (MenuRole menuRole:authList) {
            authSet.addAll(GsonUtil.toListString(menuRole.getAuth_list()));
        }
        LoginUserInfo loginUserInfo = BeanCopyUtil.copy(admin, LoginUserInfo.class);
        loginUserInfo.setAuth_list(authSet);
        loginUserInfo.setAuth_data_code_list(authDataAdminService.listByAdminId(admin.getId()));
        return loginUserInfo;
    }

    private String insertLoginLog(String ipAddress,Admin admin){
        LoginLog loginLog=new LoginLog();
        loginLog.setId(IDGenerateUtil.uuid());
        loginLog.setAdmin_id(admin.getId());
        loginLog.setIp_address(ipAddress);
        loginLogMapper.insert(loginLog);
        return loginLog.getId();
    }

}
