package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hua
 */
@Slf4j
@SpringBootApplication
@EnableScheduling
public class WebAdminApplication {

    public static void main(String[] args){
        SpringApplication.run(WebAdminApplication.class, args);
        log.info("admin server start......");
    }

}
