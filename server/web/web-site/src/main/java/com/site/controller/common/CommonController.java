package com.site.controller.common;

import com.common.aspect.annotation.LimitTime;
import com.common.redis.RedisUtils;
import com.common.domain.response.JSONResult;
import com.site.controller.common.constant.CommonConstant;
import com.site.controller.common.vo.UserCaptchaReqVo;
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
public class CommonController  extends BaseController {

    @Autowired
    RedisUtils redisUtils;

    @RequestMapping(value = CommonUrl.SAVE_VERIFY)
    @LimitTime
    public JSONResult saveVerify(@RequestBody UserCaptchaReqVo userCaptchaReqVo){
        redisUtils.objectSet(CommonConstant.VERIFY.getKey()+userCaptchaReqVo.getPic_verify().toLowerCase(),
                CommonConstant.VERIFY.getTimeOut(),userCaptchaReqVo.getPic_verify().toLowerCase() );
        return  JSONResult.success();
    }
}
