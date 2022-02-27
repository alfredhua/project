package com.admin.web.controller.auth.vo.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@ApiModel
@Getter
@Setter
public class AuthDataRespVo {

    @ApiModelProperty(value = "id号")
    private long id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime create_time;

}
