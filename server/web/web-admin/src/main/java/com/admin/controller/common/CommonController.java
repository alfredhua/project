package com.admin.controller.common;

import com.admin.constants.CommonConstant;
import com.admin.controller.common.vo.UserCaptchaReqVo;
import com.common.aspect.annotation.LimitTime;
import com.common.middle.redis.RedisUtils;
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


    @RequestMapping(value = CommonUrl.SAVE_CAPTCHA)
    @LimitTime
    public void saveCaptcha(@RequestBody UserCaptchaReqVo userCaptchaReqVo){
        RedisUtils.objectSet(CommonConstant.ADMIN_CAPTCHA.getKey()+userCaptchaReqVo.getPic_verify().toLowerCase(),
                CommonConstant.ADMIN_CAPTCHA.getTimeOut(),userCaptchaReqVo.getPic_verify().toLowerCase());
    }



}
