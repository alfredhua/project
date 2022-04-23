package com.pro.api.auth.login;

import lombok.Data;

@Data
public class LoginReqDTO {

    private String user_name;

    private String password;


    private String ipAddress;

}
