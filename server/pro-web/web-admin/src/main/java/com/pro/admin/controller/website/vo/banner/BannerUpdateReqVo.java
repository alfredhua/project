package com.pro.admin.controller.website.vo.banner;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @auth guozhenhua
 * @date 2018/11/10
 */
@Getter
@Setter
public class BannerUpdateReqVo implements Serializable {


    @ApiModelProperty(value = "id")
    @NotNull(message = "id不能为空")
    private long id;

    @ApiModelProperty(value = "上传图片的地址")
    private String url;

    @ApiModelProperty(value = "名称")
    @NotNull(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "类型")
    @NotNull(message = "类型不能为空")
    private String type;

    @ApiModelProperty(value = "跳转url")
    private String href;


    @ApiModelProperty(value = "是否开启")
    @NotNull(message = "开启不能为空")
    private short enable;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "排序不能为空")
    private Integer order;

    @ApiModelProperty(value = "数据权限码")
    @NotNull(message="数据权限不能为空")
    private String auth_data_code;

}
