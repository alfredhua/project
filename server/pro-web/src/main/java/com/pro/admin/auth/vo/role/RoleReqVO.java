package com.pro.admin.auth.vo.role;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Api
@Getter
@Setter
public class RoleReqVO {

    @ApiModelProperty(value = "名称")
    @NotNull(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "角色权限")
    @NotNull(message = "角色权限不能为空")
    private String auth_list;

    @ApiModelProperty(value = "备注")
    private String comment;

}
