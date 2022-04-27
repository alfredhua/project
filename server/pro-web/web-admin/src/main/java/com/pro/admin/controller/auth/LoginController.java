package com.pro.admin.controller.auth;

import com.auth.service.LoginService;
import com.common.api.constants.RedisConstant;
import com.common.api.constants.SysErrorCodeEnum;
import com.common.api.exception.ResultException;
import com.common.redis.client.RedisClient;
import com.common.util.BeanCopyUtil;
import com.common.util.IPUtil;
import com.common.util.LoginUtil;
import com.pro.admin.controller.auth.vo.login.LoginReqVo;
import com.pro.admin.controller.auth.vo.login.LoginRespVo;
import com.pro.api.entity.auth.LoginReqDto;
import com.pro.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "登录")
@RestController
@RequestMapping(value = "/",method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class LoginController extends BaseController {

    @Autowired
    LoginService loginService;

    @ApiOperation(value="登录")
    @RequestMapping(value = AuthUrl.LOGIN,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
    public LoginRespVo login(@RequestBody @Valid LoginReqVo loginRequestVo ) throws Exception {
        String s = RedisClient.objectGet(RedisConstant.ADMIN_CAPTCHA.getKey() + loginRequestVo.getVerify().toLowerCase());
        if (!loginRequestVo.getVerify().equalsIgnoreCase(s)){
            throw ResultException.error(SysErrorCodeEnum.VERIFY_ERROR);
        }
        LoginReqDto loginReqDTO = BeanCopyUtil.copy(loginRequestVo, LoginReqDto.class);
        loginReqDTO.setIpAddress(IPUtil.getIpAddress());
        return resultReturn(loginService.login(loginReqDTO), LoginRespVo.class);
    }

    @ApiOperation(value="登出")
    @RequestMapping(value = AuthUrl.LOGOUT,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
    public void logout(){
        RedisClient.del(RedisConstant.ADMIN_INFO.getKey()+ LoginUtil.getLoginUser().getToken());
    }

}
