package com.auth.constants;

import lombok.Getter;

@Getter
public enum AuthConstant {

    ADMIN_INFO("admin_info:",2*60*60),       //管理端用户信息
    ADMIN_CAPTCHA("admin_captcha:",10*60);   //图片验证码

    private String key;

    private Long timeOut;


    AuthConstant(String key, long timeOut) {
        this.key = key;
        this.timeOut = timeOut;
    }


}
