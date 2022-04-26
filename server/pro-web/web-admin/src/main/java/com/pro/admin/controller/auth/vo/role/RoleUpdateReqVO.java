package com.pro.admin.controller.auth.vo.role;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Api
@Getter
@Setter
public class RoleUpdateReqVO {

    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空")
    private long id;

    @ApiModelProperty(value = "角色名称")
    @NotNull(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "权限列表")
    @NotNull(message = "权限不能为空")
    private String auth_list;

    @ApiModelProperty(value = "备注")
    private String comment;
}
