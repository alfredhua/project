package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

/**
 * @author guozhenhua
 * @date 2020/04/16
 */
public class WebSiteServerApplication {
    private static Logger logger = LoggerFactory.getLogger(WebsiteCore.class);


    public static void main(String[] args){

        SpringApplication.run(WebsiteCore.class,args);

        logger.info("website server  start......");

    }
}
