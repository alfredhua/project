package com.blog.constant;

import lombok.Getter;

@Getter
public enum NavigateOneTypeEnum {

    INDEX("INDEX","首页"),
    BOOK("BOOK","图书"),
    MANUAL("MANUAL","操作手册"),

    ;

    private String type;

    private String name;


    NavigateOneTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
