package com.pro.message.dto.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeChatUserInfo {
    private String id;
    private long userId;
    private String openid;
    private String nickname;
    private int sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private String unionid;

}
