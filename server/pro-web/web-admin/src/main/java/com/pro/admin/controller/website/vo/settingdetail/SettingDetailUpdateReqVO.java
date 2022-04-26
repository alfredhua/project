package com.pro.admin.controller.website.vo.settingdetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Api
@Getter
@Setter
public class SettingDetailUpdateReqVO {



    @ApiModelProperty(value = "设置类型")
    @NotNull(message = "类型不能为空")
    private String type;

    @ApiModelProperty(value = "设置内容")
    @NotNull(message = "内容不能为空")
    private String content;

    @ApiModelProperty(value = "设置url")
    private String url;

    @ApiModelProperty(value = "j介绍")
    private String introduce;



}
