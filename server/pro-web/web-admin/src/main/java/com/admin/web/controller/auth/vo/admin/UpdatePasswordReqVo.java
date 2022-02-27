package com.admin.web.controller.auth.vo.admin;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdatePasswordReqVo{

  @NotNull(message = "旧密码不能为空")
  String old_password;

  @NotNull(message = "新密码不能为空")
  String new_password;

  @NotNull(message = "确认密码不能为空")
  String confirm_password;


}
