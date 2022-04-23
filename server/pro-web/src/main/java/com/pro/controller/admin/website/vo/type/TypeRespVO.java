package com.pro.controller.admin.website.vo.type;

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
public class TypeRespVO {

    @ApiModelProperty(value = "id号")
    private Long id;

    @ApiModelProperty(value = "文章类型")
    private String type;

    @ApiModelProperty(value = "文章类型名称")
    private String name;

    @ApiModelProperty(value = "是否激活:0未激活,1:激活")
    private Short status;

    @ApiModelProperty(value = "简介")
    private String introduce;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime create_time;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime update_time;

    @ApiModelProperty(value = "是否删除,0:未删除,1删除")
    private Short del;
}
