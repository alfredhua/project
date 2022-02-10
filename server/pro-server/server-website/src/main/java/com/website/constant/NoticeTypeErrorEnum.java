package com.website.constant;

import lombok.Getter;

/**
 * @auth guozhenhua
 * @date 2018/10/30
 */
@Getter
public enum NoticeTypeErrorEnum {

    EXIST("EXIST","该类型已经存在"),
    GET_ERROR("GET_ERROR","不存在"),
    UPDATE_ERROR("UPDATE_ERROR","更新错误");

    private String code;
    private String msg;

    NoticeTypeErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
