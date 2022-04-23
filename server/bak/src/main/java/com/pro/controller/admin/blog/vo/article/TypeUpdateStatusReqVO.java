package com.pro.controller.admin.blog.vo.article;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author guozhenhua
 * @date 2019/08/31
 */
@Api
@Getter
@Setter
public class TypeUpdateStatusReqVO {

    @ApiModelProperty(value = "文章类型id号")
    @NotNull(message = "id不能为空")
    long id;

    @ApiModelProperty(value = "文章类型状态")
    @NotNull(message = "状态不能为空")
    int status;

}
