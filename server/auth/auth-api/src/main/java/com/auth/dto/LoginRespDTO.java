package com.auth.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * @auth guozhenhua
 * @date 2018/11/06
 */
@Getter
@Setter
public class LoginRespDTO implements Serializable {


    private String token;

    private Set<String> auth_list;

    public LoginRespDTO(String token, Set<String> auth_list) {
        this.token = token;
        this.auth_list = auth_list;
    }
}
