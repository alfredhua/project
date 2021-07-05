package com.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

/**
 * @author guozhenhua
 * @date 2020/04/16
 */
public class AuthServerApplication {

    private static Logger logger = LoggerFactory.getLogger(AuthCore.class);


    public static void main(String[] args){

        SpringApplication.run(AuthCore.class,args);

        logger.info("auth server  start......");

    }
}
