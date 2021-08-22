package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hua
 */
@SpringBootApplication
@EnableScheduling
public class WebAdminApplication {

    private static Logger logger = LoggerFactory.getLogger(WebAdminApplication.class);
    

    public static void main(String[] args){
        SpringApplication.run(WebAdminApplication.class, args);
        logger.info("admin server start......");
    }

}
