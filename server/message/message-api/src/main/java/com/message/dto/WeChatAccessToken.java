package com.message.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class WeChatAccessToken implements Serializable{

    private String access_token;
    private int expires_in;
    private LocalDateTime expires_at;
    private int errcode;
    private String errmsg;

}
