package com.website.constant;

import lombok.Getter;

@Getter
public enum WebSiteConstant {

    BANNER_LIST("banner_list:",60*60); //前端bannerList

    private String key;

    private Long timeOut;


    WebSiteConstant(String key, long timeOut) {
        this.key = key;
        this.timeOut = timeOut;
    }


}
