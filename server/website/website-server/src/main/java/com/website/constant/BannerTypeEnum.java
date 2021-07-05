package com.website.constant;

import lombok.Getter;

/**
 * @auth guozhenhua
 * @date 2018/11/10
 */
@Getter
public enum  BannerTypeEnum {

    PC("PC","pc"),
    APP("APP","app"),
    XXC("XXC","小程序"),
    H5("H5","手机站");

    private String type;

    private String msg;


    BannerTypeEnum(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}
