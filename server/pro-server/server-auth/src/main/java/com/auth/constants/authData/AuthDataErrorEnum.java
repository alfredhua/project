package com.auth.constants.authData;

import lombok.Getter;

/**
 * @auth guozhenhua
 * @date 2018/11/06
 */
@Getter
public enum AuthDataErrorEnum {

    EDIT_ERROR("EDIT_ERROR","修改失败"),
    CODE_EXIST("CODE_EXIST","code已存在"),
    ADD_ERROR("ADD_ERROR","添加失败");


    AuthDataErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

}
