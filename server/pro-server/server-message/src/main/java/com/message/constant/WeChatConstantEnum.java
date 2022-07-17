package com.message.constant;

import lombok.Getter;

/**
 * Created by guozhenhua
 * date 2020/8/6.
 */
@Getter
public enum WeChatConstantEnum {

    ACCESS_TOKEN("access_token:",7000) ,     //微信token
    WEI_XIN_IP("wei_xin_ip:",24*60*60*30);    //微信ip

    private String key;

    private Long timeOut;

    WeChatConstantEnum(String key, long timeOut) {
        this.key = key;
        this.timeOut = timeOut;
    }
}
