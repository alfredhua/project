package com.admin.controller.blog.vo.type;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
/**
 * @auth guozhenhua
 * @date 2019/08/31
 */
@Api
@Getter
@Setter
public class TypeCreateReqVO {

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "类型名称")
    private String name;

    @ApiModelProperty(value = "类型简介")
    private String introduce;

}
