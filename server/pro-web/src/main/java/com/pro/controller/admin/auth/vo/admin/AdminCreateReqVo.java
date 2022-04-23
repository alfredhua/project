package com.pro.controller.admin.auth.vo.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ApiModel(value="adminCreateReqVo")
public class AdminCreateReqVo {

    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空")
    private String user_name;

    @ApiModelProperty(value = "角色id")
    @NotNull(message = "角色不能为空")
    private String role_id_list;

    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "email")
    @NotNull(message = "email不能为空")
    private String email;

    @ApiModelProperty(value = "状态")
    private short status;

    @ApiModelProperty(value = "数据权限编码")
    @NotNull(message = "数据权限不能为空")
    private List<String> auth_code_list;
}
