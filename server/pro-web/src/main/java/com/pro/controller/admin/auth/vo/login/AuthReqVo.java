package com.pro.controller.admin.auth.vo.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @auth guozhenhua
 * @date 2018/11/06
 */
@Getter
@Setter
public class AuthReqVo {

    @NotNull(message = "token不能为空")
    private String token;

}
