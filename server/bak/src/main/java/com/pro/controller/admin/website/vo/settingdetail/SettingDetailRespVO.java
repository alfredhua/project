package com.pro.controller.admin.website.vo.settingdetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Api
@Getter
@Setter
public class SettingDetailRespVO  {

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "url地址")
    private String url;

    @ApiModelProperty(value = "介绍")
    private String introduce;



}
