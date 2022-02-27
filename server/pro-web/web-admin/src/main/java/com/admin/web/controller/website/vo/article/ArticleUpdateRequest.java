package com.admin.web.controller.website.vo.article;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @auth guozhenhua
 * @date 2018/12/20
 */
@Api
@Getter
@Setter
public class ArticleUpdateRequest {


    @ApiModelProperty(value = "id号")
    @NotNull(message = "id不能为空")
    private long id;

    @ApiModelProperty(value = "类型")
    @NotNull(message = "类型不能为空")
    private String type;

    @ApiModelProperty(value = "标题")
    @NotNull(message = "title不能为空")
    private String title;

    @ApiModelProperty(value = "内容")
    @NotNull(message = "内容不能为空")
    private String context;


    @ApiModelProperty(value = "是否发布,0:未发布,1发布")
    @NotNull(message = "是否发布不能为空")
    private Integer publish;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "排序不能为空")
    private Integer ordering;

    @ApiModelProperty(value = "权限码")
    @NotNull(message = "权限码不能为空")
    private String auth_data_code;

}
