package com.pro.admin.controller.website.vo.news;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @auth guozhenhua
 * @date 2019/07/12
 */
@Api
@Getter
@Setter
public class NewsUpdateReqVO {


  @ApiModelProperty(value = "id")
  @NotNull(message = "id不能为空")
  private long id;

  @ApiModelProperty(value = "标题")
  @NotNull(message = "名称不能为空")
  private String title;

  @ApiModelProperty(value = "来源")
  private String source;

  @ApiModelProperty(value = "来源地址")
  private String source_url;

  @ApiModelProperty(value = "列表预览图片")
  @NotNull(message = "图片不能为空")
  private String pic;

  @ApiModelProperty(value = "描述")
  private String introduce;

  @ApiModelProperty(value = "内容")
  @NotNull(message = "文章内容不能为空")
  private String context;

  @ApiModelProperty(value = "权限码")
  @NotNull(message = "权限码不能为空")
  private String auth_data_code;


}
