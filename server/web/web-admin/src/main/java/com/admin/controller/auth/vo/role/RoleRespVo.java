package com.admin.controller.auth.vo.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Api
@Getter
@Setter
public class RoleRespVo  {

    @ApiModelProperty(value = "id号")
    private long id;

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "权限列表")
    private String auth_list;

    @ApiModelProperty(value = "备注")
    private String comment;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime create_time;

    @ApiModelProperty(value = "状态")
    private boolean status;


}
