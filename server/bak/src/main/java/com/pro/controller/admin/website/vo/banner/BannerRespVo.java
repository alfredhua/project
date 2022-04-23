package com.pro.controller.admin.website.vo.banner;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @auth guozhenhua
 * @date 2018/11/10
 */
@Api
@Getter
@Setter
public class BannerRespVo implements Serializable {

    @ApiModelProperty(value = "id号")
    private long id;

    @ApiModelProperty(value = "banner地址")
    private String url;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "跳转地址")
    private String href;

    @ApiModelProperty(value = "是否开启")
    private short enable;

    @ApiModelProperty(value = "排序")
    private Integer order;

    @ApiModelProperty(value = "权限码")
    private String auth_data_code;
}
