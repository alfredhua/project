package com.auth.constants.role;

import lombok.Getter;

/**
 * @auth guozhenhua
 * @date 2018/11/06
 */
@Getter
public enum  RoleErrorEnum {

    EDIT_ERROR("EDIT_ERROR","修改失败"),
    ADD_ERROR("ADD_ERROR","添加失败");


    RoleErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

}
