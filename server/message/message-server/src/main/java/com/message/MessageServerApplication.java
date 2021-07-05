package com.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

/**
 * @author guozhenhua
 * @date 2020/04/16
 */
public class MessageServerApplication {

    private static Logger logger = LoggerFactory.getLogger(MessageServerApplication.class);

    public static void main(String[] args){

        SpringApplication.run(MessageCore.class,args);

        logger.info("message server  start......");

    }
}
