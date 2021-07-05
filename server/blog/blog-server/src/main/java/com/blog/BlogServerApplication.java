package com.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

/**
 * @author guozhenhua
 * @date 2020/04/16
 */
public class BlogServerApplication {
    private static Logger logger = LoggerFactory.getLogger(BlogCore.class);


    public static void main(String[] args){

        SpringApplication.run(BlogCore.class,args);

        logger.info("blog server  start......");

    }
}
