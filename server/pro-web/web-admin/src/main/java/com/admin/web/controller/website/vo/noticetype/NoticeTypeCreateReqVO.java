package com.admin.web.controller.website.vo.noticetype;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @auth guozhenhua
 * @date 2018/12/20
 */
@Api
@Getter
@Setter
public class NoticeTypeCreateReqVO {


    @ApiModelProperty(value = "文章类型名称")
    private String name;

    @ApiModelProperty(value = "文章类型")
    private String type;

    @ApiModelProperty(value = "权限码")
    private String auth_data_code;
}
