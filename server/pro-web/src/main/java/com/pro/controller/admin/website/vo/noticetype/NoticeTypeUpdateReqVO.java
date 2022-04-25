package com.pro.controller.admin.website.vo.noticetype;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @auth guozhenhua
 * @date 2018/12/20
 */
@Api
@Getter
@Setter
public class NoticeTypeUpdateReqVO {

    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空")
    private long id;

    @ApiModelProperty(value = "名称")
    @NotNull(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "类型")
    @NotNull(message = "类型不能为空")
    private String type;

    @ApiModelProperty(value = "权限码")
    private String auth_data_code;
}
