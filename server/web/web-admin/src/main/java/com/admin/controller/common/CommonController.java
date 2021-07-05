package com.admin.controller.common;

import com.common.aspect.annotation.LimitTime;
import com.common.redis.RedisUtils;
import com.common.domain.response.JSONResult;
import com.admin.constants.CommonConstant;
import com.admin.controller.common.vo.UserCaptchaReqVo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommonController  extends AdminBaseController {


    @Autowired
    RedisUtils redisUtils;

    @RequestMapping(value = CommonUrl.SAVE_CAPTCHA)
    @LimitTime
    public void saveCaptcha(@RequestBody UserCaptchaReqVo userCaptchaReqVo){
        redisUtils.objectSet(CommonConstant.ADMIN_CAPTCHA.getKey()+userCaptchaReqVo.getPic_verify().toLowerCase(),
                CommonConstant.ADMIN_CAPTCHA.getTimeOut(),userCaptchaReqVo.getPic_verify().toLowerCase());
    }



}
