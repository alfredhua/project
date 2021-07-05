package com.admin.controller.auth.vo.admin;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class AdminUpdateReqVo {

    @NotNull(message = "id不能为空")
    private long id;
    @NotNull(message = "用户名不能为空")
    private String user_name;
    @NotNull(message = "角色不能为空")
    private String role_id_list;
    @NotNull(message = "手机号不能为空")
    private String phone;
    @NotNull(message = "email不能为空")
    private String email;
    private short status;

    @NotNull(message = "权限编码")
    private List<String> auth_code_list;
}
