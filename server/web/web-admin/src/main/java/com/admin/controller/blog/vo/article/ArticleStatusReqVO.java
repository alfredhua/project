package com.admin.controller.blog.vo.article;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Api
@Getter
@Setter
public class ArticleStatusReqVO {

  @ApiModelProperty(value = "id号")
  @NotNull(message = "id不能为空")
  private Long id;


  @ApiModelProperty(value = "状态")
  @NotNull(message = "状态不能为空")
  private short status;



}
