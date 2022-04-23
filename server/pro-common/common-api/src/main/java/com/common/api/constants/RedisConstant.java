package com.common.api.constants;

import lombok.Getter;

@Getter
public enum RedisConstant {

    ADMIN_INFO("admin_info:",2*60*60,"admin用户登录信息"),
    ADMIN_CAPTCHA("admin_captcha:",10*60,"图片验证码"),
    VERIFY("verify:",30*60,"短信验证码过期");

    private String key;

    private Long timeOut;

    private String msg;

    RedisConstant(String key, long timeOut,String msg) {
        this.key = key;
        this.timeOut = timeOut;
        this.msg=msg;
    }

}