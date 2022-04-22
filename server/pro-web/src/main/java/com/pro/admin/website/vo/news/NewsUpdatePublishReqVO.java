package com.pro.admin.website.vo.news;

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
public class NewsUpdatePublishReqVO {


  @ApiModelProperty(value = "id")
  @NotNull(message = "id不能为空")
  private long id;

  @ApiModelProperty(value = "是否发布")
  @NotNull(message = "publish不能为空")
  private short publish;




}
