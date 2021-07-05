package com.auth.constants.role;

/**
 * @auth guozhenhua
 * @date 2018/11/05
 */
public enum RoleStatusEnum {

    active((short)0,"激活"),
    not_active((short)1,"禁用");


    RoleStatusEnum(short status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private short status;
    private String msg;
}
