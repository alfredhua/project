package com.admin.controller.auth;

import com.auth.constants.AuthConstant;
import com.auth.dto.LoginAdminRespDTO;
import com.auth.dto.LoginReqDTO;
import com.auth.service.LoginService;
import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.redis.RedisUtils;
import com.common.util.BeanCopyUtil;
import com.admin.controller.auth.vo.login.LoginCheckReqVo;
import com.admin.controller.auth.vo.login.LoginReqVo;
import com.admin.controller.auth.vo.login.LoginRespVo;
import com.admin.controller.common.AdminBaseController;
import com.common.util.IPUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "登录")
@RestController
public class LoginController extends AdminBaseController {

    @Autowired
    LoginService loginService;

    @Autowired
    RedisUtils redisUtils;

    @ApiOperation(value="登录")
    @RequestMapping(value = AuthUrl.LOGIN,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
    public LoginRespVo login(@RequestBody @Valid LoginReqVo loginRequestVo, BindingResult result)throws Exception{
        String s = redisUtils.objectGet(AuthConstant.ADMIN_CAPTCHA.getKey() + loginRequestVo.getVerify().toLowerCase());
        if (!loginRequestVo.getVerify().equalsIgnoreCase(s)){
            throw ResultException.error(SysErrorCodeEnum.VERIFY_ERROR);
        }
        LoginReqDTO loginReqDTO = BeanCopyUtil.copy(loginRequestVo, LoginReqDTO.class);
        loginReqDTO.setIpAddress(IPUtils.getIpAddress());
        return resultReturn(loginService.login(loginReqDTO), LoginRespVo.class);
    }

    @ApiOperation(value="检查是否登录")
    @RequestMapping(value = AuthUrl.CHECK_LOGIN,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
    public void checkLogin(@RequestBody  @Valid LoginCheckReqVo loginCheckReqVo, BindingResult result){
        LoginAdminRespDTO admin = redisUtils.objectGet(AuthConstant.ADMIN_INFO.getKey() + loginCheckReqVo.getToken());
        if (!ObjectUtils.isEmpty(admin)){
            redisUtils.objectSet(AuthConstant.ADMIN_INFO.getKey()+loginCheckReqVo.getToken(), AuthConstant.ADMIN_INFO.getTimeOut(), admin);
        }
    }

    @ApiOperation(value="登出")
    @RequestMapping(value = AuthUrl.LOGOUT,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
    public void logout(){
        redisUtils.del(AuthConstant.ADMIN_INFO.getKey()+getToken());
    }

}
