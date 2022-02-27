package com.admin.web.controller.auth.vo.admin;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AdminSetAuthDataReq {

  @NotNull(message="id不能为空")
  long id;


  @NotNull(message="权限编码不能为空")
  String auth_data_code;

}
