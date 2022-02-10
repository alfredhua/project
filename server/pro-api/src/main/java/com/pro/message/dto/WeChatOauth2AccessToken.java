package com.pro.message.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class WeChatOauth2AccessToken implements Serializable{

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private int errcode;
    private String errmsg;

}
