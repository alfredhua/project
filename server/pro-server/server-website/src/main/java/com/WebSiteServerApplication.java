package com;

import com.common.util.LogUtil;
import org.springframework.boot.SpringApplication;

/**
 * @author guozhenhua
 * @date 2020/04/16
 */
public class WebSiteServerApplication {

    public static void main(String[] args){
        SpringApplication.run(WebsiteCore.class,args);
        LogUtil.info("website server  start......");
    }
}
