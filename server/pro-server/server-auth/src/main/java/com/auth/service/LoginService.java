package com.auth.service;

import com.auth.constants.AuthConstant;
import com.auth.constants.admin.AdminStatusEnum;
import com.auth.dao.AdminMapper;
import com.auth.dao.LoginLogMapper;
import com.auth.dao.MenuRoleMapper;
import com.common.api.constants.SysErrorCodeEnum;
import com.common.api.exception.ResultException;
import com.common.redis.client.RedisClient;
import com.common.util.BeanCopyUtil;
import com.common.util.GsonUtil;
import com.common.util.IDGenerateUtil;
import com.common.util.MessageDigestUtil;
import com.google.gson.reflect.TypeToken;
import com.pro.auth.dto.LoginAdminRespDTO;
import com.pro.auth.dto.LoginReqDTO;
import com.pro.auth.dto.LoginRespDTO;
import com.pro.auth.dto.entity.Admin;
import com.pro.auth.dto.entity.LoginLog;
import com.pro.auth.dto.entity.MenuRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

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

    public LoginRespDTO login(LoginReqDTO loginReqDTO)throws Exception{
            if (ObjectUtils.isEmpty(loginReqDTO.getUser_name()) || ObjectUtils.isEmpty(loginReqDTO.getPassword())) {
                throw ResultException.error(SysErrorCodeEnum.NULL_ERROR);
            }
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
            if (admin.getStatus() == AdminStatusEnum.FROZEN.getCode()) {
                throw ResultException.error(SysErrorCodeEnum.FROZEN);
            }
            String token = insertLoginLog(loginReqDTO.getIpAddress(), admin);
            LoginAdminRespDTO loginAdminRespDTO = loginUser(admin);
            RedisClient.objectSet(AuthConstant.ADMIN_INFO.getKey() + token, AuthConstant.ADMIN_INFO.getTimeOut(), loginAdminRespDTO);
            return new LoginRespDTO(token, loginAdminRespDTO.getAuth_list());
    }

    
    private LoginAdminRespDTO  loginUser(Admin admin){
        //获取所有的roleIds
        Set<Long> roleIds=GsonUtil.gson.fromJson(admin.getRole_id_list(),new TypeToken<Set<Long>>(){}.getType());
        List<MenuRole> authList = menuRoleMapper.listByIds(roleIds);
        //所有的authDictionary id的set
        Set<String> authSet = new HashSet<>();
        for (MenuRole menuRole:authList) {
            authSet.addAll(GsonUtil.gson.fromJson(menuRole.getAuth_list(),new TypeToken<List<String>>(){}.getType()));
        }
        LoginAdminRespDTO loginAdminRespDTO = BeanCopyUtil.copy(admin, LoginAdminRespDTO.class);
        loginAdminRespDTO.setAuth_list(authSet);
        loginAdminRespDTO.setPassword(null);
        loginAdminRespDTO.setAuth_code_list(authDataAdminService.listByAdminId(admin.getId()));
        return loginAdminRespDTO;
    }

    private String insertLoginLog(String ipAddress,Admin admin){
        LoginLog loginLog=new LoginLog();
        loginLog.setId(IDGenerateUtil.uuid());
        loginLog.setAdminId(admin.getId());
        loginLog.setIpAddress(ipAddress);
        loginLogMapper.insert(loginLog);
        return loginLog.getId();
    }


}
