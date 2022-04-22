package com.pro.admin.auth.vo.admin;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ResetAdminPasswordReq {

  @NotNull(message="id不能为空")
  long id;

}
