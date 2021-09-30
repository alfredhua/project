package com;

import com.common.util.LogUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hua
 */
@SpringBootApplication
@EnableScheduling
public class WebAdminApplication {

    public static void main(String[] args){
        SpringApplication.run(WebAdminApplication.class, args);
        LogUtils.info("admin server start......");
    }

}
