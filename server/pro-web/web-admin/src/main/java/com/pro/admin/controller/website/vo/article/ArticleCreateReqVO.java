package com.pro.admin.controller.website.vo.article;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @auth guozhenhua
 * @date 2019/08/31
 */
@Api
@Getter
@Setter
public class ArticleCreateReqVO {

    @ApiModelProperty(value = "标题")
    @NotNull(message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "是否转载")
    @NotNull(message = "转载不能为空")
    private short reprint;

    @ApiModelProperty(value = "文章编辑类型")
    @NotNull(message = "文章编辑类型")
    private short content_type;

    @ApiModelProperty(value = "文章分类")
    @NotNull(message = "文章分类不能为空")
    private String type;

    @ApiModelProperty(value = "文章简介")
    @NotNull(message = "简介不能为空")
    private String introduce;

    @ApiModelProperty(value = "文章内容")
    @NotNull(message = "内容不能为空")
    private String context;

    @ApiModelProperty(value = "图片封面url")
    @NotNull(message = "图片封面url")
    private String pic_url;

}
