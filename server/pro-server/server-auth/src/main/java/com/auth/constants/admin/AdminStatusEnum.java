package com.auth.constants.admin;

import lombok.Getter;

/**
 * @auth guozhenhua
 * @date 2018/11/06
 */
@Getter
public enum  AdminStatusEnum {

    FROZEN((short)0,"冻结"),
    ACTIVE((short)1,"激活"),;

     AdminStatusEnum(short code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private short code;
    private String msg;


}
