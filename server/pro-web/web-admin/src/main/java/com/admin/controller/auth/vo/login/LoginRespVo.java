package com.admin.controller.auth.vo.login;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @auth guozhenhua
 * @date 2018/11/06
 */

@Api
@Getter
@Setter
public class LoginRespVo {

    @ApiModelProperty(value = "toekn")
    private String token;

    @ApiModelProperty(value = "权限")
    private Set<String> auth_list;

}
