package com.pro.api.auth;

import lombok.Data;

@Data
public class LoginReqDto {

    private String user_name;

    private String password;


    private String ipAddress;

}
