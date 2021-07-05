package com.message.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class WeChatJsapiTicket implements Serializable{

    private String errcode;
    private String errmsg;
    // 微信jsapi_ticket
    private String ticket;
    // 有效时间
    private int expires_in;
    private LocalDateTime expires_at;


}
