package com.website.constant;

import lombok.Getter;

/**
 * @auth guozhenhua
 * @date 2018/10/30
 */
@Getter
public enum NoticeTypeActiveEnum {

    NOT_ACTIVE((short)0,"未激活"),
    ACTIVE((short)1,"激活");

    private short code;
    private String msg;

    NoticeTypeActiveEnum(short code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
