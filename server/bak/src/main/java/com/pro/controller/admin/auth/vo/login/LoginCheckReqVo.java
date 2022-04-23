package com.pro.controller.admin.auth.vo.login;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @auth guozhenhua
 * @date 2018/11/07
 */
@Api
@Getter
@Setter
public class LoginCheckReqVo {


    @ApiModelProperty(value = "token")
    @NotNull(message = "token不能为空")
    private String token;
}
