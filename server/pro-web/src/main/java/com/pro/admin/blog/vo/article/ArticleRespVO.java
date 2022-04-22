package com.pro.admin.blog.vo.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @auth guozhenhua
 * @date 2019/08/31
 */
@Api
@Getter
@Setter
public class ArticleRespVO {

    @ApiModelProperty(value = "id号")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "文章分类")
    private String type;

    @ApiModelProperty(value = "是否转载")
    private Short reprint;

    @ApiModelProperty(value = "点击数")
    private Integer click_count;

    @ApiModelProperty(value = "喜欢数")
    private Integer like_count;

    @ApiModelProperty(value = "内容")
    private String context;

    @ApiModelProperty(value = "文章编辑类型")
    private short content_type;

    @ApiModelProperty(value = "介绍")
    private String introduce;

    @ApiModelProperty(value = "图片封面url")
    private String pic_url;

    @ApiModelProperty(value = "1:待发布，2:已发布，")
    private Short status;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime create_time;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime update_time;

    @ApiModelProperty(value = "是否删除,0:未删除,1删除")
    private Short del;

}
