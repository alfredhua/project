package com.pro.admin.auth;

import com.pro.admin.common.AdminBaseController;
import com.pro.admin.auth.vo.login.LoginReqVo;
import com.pro.admin.auth.vo.login.LoginRespVo;
import com.auth.constants.AuthConstant;
import com.auth.service.LoginService;
import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.middle.redis.RedisUtil;
import com.common.util.BeanCopyUtil;
import com.common.util.IPUtil;
import com.common.util.LoginUtil;
import com.pro.auth.dto.LoginReqDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @ApiOperation(value="登录")
    @RequestMapping(value = AuthUrl.LOGIN,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
    public LoginRespVo login(@RequestBody @Valid LoginReqVo loginRequestVo, BindingResult result)throws Exception{
        String s = RedisUtil.objectGet(AuthConstant.ADMIN_CAPTCHA.getKey() + loginRequestVo.getVerify().toLowerCase());
        if (!loginRequestVo.getVerify().equalsIgnoreCase(s)){
            throw ResultException.error(SysErrorCodeEnum.VERIFY_ERROR);
        }
        LoginReqDTO loginReqDTO = BeanCopyUtil.copy(loginRequestVo, LoginReqDTO.class);
        loginReqDTO.setIpAddress(IPUtil.getIpAddress());
        return resultReturn(loginService.login(loginReqDTO), LoginRespVo.class);
    }

    @ApiOperation(value="登出")
    @RequestMapping(value = AuthUrl.LOGOUT,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
    public void logout(){
        RedisUtil.del(AuthConstant.ADMIN_INFO.getKey()+ LoginUtil.getLoginUser().getToken());
    }

}
