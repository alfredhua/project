package com;

import com.common.util.LogUtils;
import org.springframework.boot.SpringApplication;

/**
 * @author guozhenhua
 * @date 2020/04/16
 */
public class WebSiteServerApplication {

    public static void main(String[] args){
        SpringApplication.run(WebsiteCore.class,args);
        LogUtils.info("website server  start......");
    }
}
