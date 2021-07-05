package com.auth.service;

import com.auth.constants.AuthConstant;
import com.auth.constants.admin.AdminStatusEnum;
import com.auth.dao.AdminMapper;
import com.auth.dao.LoginLogMapper;
import com.auth.dao.MenuRoleMapper;
import com.auth.dto.LoginAdminRespDTO;
import com.auth.dto.LoginReqDTO;
import com.auth.dto.LoginRespDTO;
import com.auth.dto.entity.Admin;
import com.auth.dto.entity.LoginLog;
import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.JSONResult;
import com.common.redis.RedisUtils;
import com.common.util.BeanCopyUtil;
import com.common.util.GsonUtils;
import com.common.util.IDGenerate;
import com.common.util.MessageDigestUtil;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    RedisUtils redisUtils;

    public JSONResult<LoginAdminRespDTO>  getLoginAdminInfo(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        LoginAdminRespDTO loginAdminRespDTO = redisUtils.objectGet(AuthConstant.ADMIN_INFO.getKey() + request.getHeader("token"));
        if (ObjectUtils.isEmpty(loginAdminRespDTO)){
            return JSONResult.error(SysErrorCodeEnum.TOKEN_INVALID.getCode(),SysErrorCodeEnum.TOKEN_INVALID.getMsg());
        }
        return JSONResult.success(loginAdminRespDTO);
    }


    public LoginRespDTO login(LoginReqDTO loginReqDTO)throws Exception{
            if (StringUtils.isEmpty(loginReqDTO.getUser_name()) || StringUtils.isEmpty(loginReqDTO.getPassword())) {
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
            redisUtils.objectSet(AuthConstant.ADMIN_INFO.getKey() + token, AuthConstant.ADMIN_INFO.getTimeOut(), loginAdminRespDTO);
            return new LoginRespDTO(token, loginAdminRespDTO.getAuth_list());

    }

    
    public JSONResult<LoginAdminRespDTO> setAdminInfoByToken(String token) {
        LoginAdminRespDTO loginAdminRespDTO= redisUtils.objectGet(AuthConstant.ADMIN_INFO.getKey() + token);
        if (!ObjectUtils.isEmpty(loginAdminRespDTO)){
            redisUtils.expire(AuthConstant.ADMIN_INFO.getKey()+token, AuthConstant.ADMIN_INFO.getTimeOut());
            return JSONResult.success(loginAdminRespDTO);
        }
        return JSONResult.error(SysErrorCodeEnum.TOKEN_INVALID.getCode(),SysErrorCodeEnum.TOKEN_INVALID.getMsg());
    }

    private LoginAdminRespDTO  loginUser(Admin admin){
        //获取所有的roleIds
        List<Object> roleIds=GsonUtils.gson.fromJson(admin.getRole_id_list(),new TypeToken<List<Object>>(){}.getType());
        List<String> authList = menuRoleMapper.getRoleByIdList(roleIds);
        //所有的authDictionary id的set
        Set<String> authSet = new HashSet<>();
        for (String str:authList) {
            List<String> list=GsonUtils.gson.fromJson(str,new TypeToken<List<String>>(){}.getType());
            if (!list.isEmpty()){
                list.forEach(item->{
                    authSet.add(item);
                });
            }
        }
        LoginAdminRespDTO loginAdminRespDTO = BeanCopyUtil.copy(admin, LoginAdminRespDTO.class);
        loginAdminRespDTO.setAuth_list(authSet);
        loginAdminRespDTO.setPassword(null);

        loginAdminRespDTO.setAuth_code_list(authDataAdminService.listByAdminId(admin.getId()));
        return loginAdminRespDTO;
    }

    private String insertLoginLog(String ipAddress,Admin admin){
        LoginLog loginLog=new LoginLog();
        loginLog.setId(IDGenerate.uuid());
        loginLog.setAdminId(admin.getId());
        loginLog.setIpAddress(ipAddress);
        loginLogMapper.insert(loginLog);
        return loginLog.getId();
    }


}
