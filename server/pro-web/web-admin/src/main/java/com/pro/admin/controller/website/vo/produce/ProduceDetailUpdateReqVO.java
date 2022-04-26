package com.pro.admin.controller.website.vo.produce;

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
public class ProduceDetailUpdateReqVO {


    @ApiModelProperty(value = "id号")
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "文章内容")
    @NotNull(message = "context不能为空")
    private String context;

}
