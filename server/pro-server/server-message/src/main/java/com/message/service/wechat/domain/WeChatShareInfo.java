package com.message.service.wechat.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeChatShareInfo {

    private String id;
    private String url;
    private String noncestr;
    private String timestamp;
    private String signature;
    private String appid;

    public WeChatShareInfo addAppId(String appid){
        this.appid=appid;
        return this;
    }


}
