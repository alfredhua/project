package com.pro.controller.admin.website.vo.partner;

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
public class PartnerUpdateReqVO {

  @ApiModelProperty(value = "id号")
  @NotNull(message = "id不能为空")
  private long id;

  @ApiModelProperty(value = "合作伙伴名称")
  @NotNull(message = "name不能为空")
  private String name;

  @ApiModelProperty(value = "跳转地址")
  @NotNull(message = "href不能为空")
  private String href;

  @ApiModelProperty(value = "图片地址")
  @NotNull(message = "pic_url不能为空")
  private String pic_url;

  @ApiModelProperty(value = "数据权限码")
  @NotNull(message = "数据权限码不能为空")
  private String auth_data_code;

}
