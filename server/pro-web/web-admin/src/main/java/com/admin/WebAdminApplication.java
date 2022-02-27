package com.admin;

import com.common.util.LogUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hua
 */
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages="com")
public class WebAdminApplication {

    public static void main(String[] args){
        SpringApplication.run(WebAdminApplication.class, args);
        LogUtil.info("admin server start......");
    }

}
