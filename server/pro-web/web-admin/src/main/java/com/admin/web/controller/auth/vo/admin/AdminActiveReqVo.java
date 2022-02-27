package com.admin.web.controller.auth.vo.admin;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AdminActiveReqVo {

    @NotNull(message = "id不能为空")
    private String id;
    @NotNull(message = "状态不能为空")
    private short isActive;
}
