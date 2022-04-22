package com.pro.admin.website.vo.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @auth guozhenhua
 * @date 2018/12/27
 */
@Api
@Getter
@Setter
public class ArticleRespVo implements Serializable {

    @ApiModelProperty(value = "id号")
    private long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "内容")
    private String context;

    @ApiModelProperty(value = "是否发布,0:未发布,1发布")
    private Integer publish;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime create_time;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime update_time;

    @ApiModelProperty(value = "是否删除")
    private Short del;

    @ApiModelProperty(value = "排序")
    private Integer ordering;

    @ApiModelProperty(value = "点击次数")
    private Integer click_count;

    @ApiModelProperty(value = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime publish_time;

    @ApiModelProperty(value = "数据权限码")
    private String auth_data_code;

}
