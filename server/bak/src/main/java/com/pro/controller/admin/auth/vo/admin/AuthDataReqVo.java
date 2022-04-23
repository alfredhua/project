package com.pro.controller.admin.auth.vo.admin;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author hua
 */
@Getter
@Setter
public class AuthDataReqVo {


    private String id;

    @NotNull(message="编码不能为空")
    private String code;

    @NotNull(message="名称不能为空")
    private String name;

}
