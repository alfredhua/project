package com.pro.common.controller;

import com.common.api.constants.RedisConstant;
import com.common.redis.client.RedisClient;
import com.pro.common.controller.vo.UserCaptchaReqVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth guozhenhua
 * @date 2018/11/19
 */
@RestController
@RequestMapping(value = CommonUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class CommonController  extends BaseController {


    @RequestMapping(value = CommonUrl.SAVE_CAPTCHA)
    public void saveCaptcha(@RequestBody UserCaptchaReqVo userCaptchaReqVo){
        RedisClient.objectSet(RedisConstant.ADMIN_CAPTCHA.getKey()+userCaptchaReqVo.getPic_verify().toLowerCase(),
                RedisConstant.ADMIN_CAPTCHA.getTimeOut(),userCaptchaReqVo.getPic_verify().toLowerCase());
    }



}
