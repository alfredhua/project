package com.pro.auth.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @auth guozhenhua
 * @date 2018/11/06
 */
@Getter
@Setter
public class LoginReqDTO implements Serializable {

    private String password;

    private String user_name;

    private String verify;

    private String key;

    private String ipAddress;
}
