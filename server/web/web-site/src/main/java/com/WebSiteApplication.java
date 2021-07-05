package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hua
 */
@SpringBootApplication
public class  WebSiteApplication {

    private static Logger logger = LoggerFactory.getLogger(WebSiteApplication.class);

    public static void main(String[] args){
        SpringApplication.run(WebSiteApplication.class, args);
        logger.info("site server start......");
    }

}
