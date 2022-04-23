package com.pro.controller.admin.website.vo.type;

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
public class TypeUpdateReqVO {

    @ApiModelProperty(value = "id号")
    private Long id;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "类型名称")
    private String name;

    @ApiModelProperty(value = "简介")
    private String introduce;



}
