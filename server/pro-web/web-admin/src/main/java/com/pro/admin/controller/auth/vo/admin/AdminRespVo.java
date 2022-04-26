package com.pro.admin.controller.auth.vo.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@ApiModel
@Getter
@Setter
public class AdminRespVo {

    @ApiModelProperty(value = "id号")
    private long id;

    @ApiModelProperty(value = "用户名")
    private String user_name;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime create_time;

    @ApiModelProperty(value = "角色id列表")
    private String role_id_list;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "状态")
    private short status;

    @ApiModelProperty(value = "菜单权限")
    private Set<String> auth_list;

    @ApiModelProperty(value = "数据权限")
    private String auth_data_code;

    @ApiModelProperty(value = "数据名称")
    private List<String> auth_code_list;

}
