package com.site.controller.common.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BaseReqVo {

  @NotNull(message = "token不能为空")
  String token;

}
