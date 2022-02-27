package com.admin.web.constants;

import lombok.Getter;

/**
 * Created by guozhenhua
 * date 2020/8/6.
 */
@Getter
public enum CommonConstant {

    ADMIN_INFO("admin_info:",2*60*60),       //管理端用户信息
    ADMIN_CAPTCHA("admin_captcha:",10*60);   //图片验证码


     private String key;
    private Long timeOut;


    CommonConstant(String key, long timeOut) {
        this.key = key;
        this.timeOut = timeOut;
    }
}
