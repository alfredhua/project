package com.admin.web.controller.website.vo.banner;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @auth guozhenhua
 * @date 2018/11/10
 */
@Api
@Getter
@Setter
public class BannerCreateReqVo implements Serializable {


    @ApiModelProperty(value = "banner地址")
    @NotNull(message="地址不能为空")
    private String url;

    @ApiModelProperty(value = "banner名称")
    @NotNull(message="名称不能为空")
    private String name;

    @ApiModelProperty(value = "类型")
    @NotNull(message="类型不能为空")
    private String type;

    @ApiModelProperty(value = "跳转地址")
    private String href;

    @ApiModelProperty(value = "是否开启")
    @NotNull(message="是否开启不能为空")
    private short enable;

    @ApiModelProperty(value = "排序")
    private Integer order;

    @ApiModelProperty(value = "数据权限码")
    @NotNull(message="数据权限不能为空")
    private String auth_data_code;

}
