package com.site.controller.common.constant;

import lombok.Getter;

@Getter
public enum CommonConstant {

    VERIFY("verify:",30*60);
    private String key;

    private Long timeOut;

    CommonConstant(String key, long timeOut) {
        this.key = key;
        this.timeOut = timeOut;
    }

}
