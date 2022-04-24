package com.pro.api.entity.auth;

import lombok.Data;

@Data
public class LoginReqDto {

    private String user_name;

    private String password;


    private String ipAddress;

}
