package com.admin.controller.auth.vo.login;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @auth guozhenhua
 * @date 2018/11/06
 */
@Api
@Getter
@Setter
public class LoginReqVo implements Serializable {

    @ApiModelProperty(value = "用户密码")
    @NotNull(message ="密码不能为空")
    private String password;

    @ApiModelProperty(value = "用户名")
    @NotNull(message ="用户名不能为空")
    private String user_name;

    @ApiModelProperty(value = "验证码")
    @NotNull(message ="验证码不能为空")
    private String verify;
}
