package com;

import com.common.util.LogUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hua
 */
@EnableScheduling
@SpringBootApplication
public class WebAdminApplication {

    public static void main(String[] args){
        SpringApplication.run(WebAdminApplication.class, args);
        LogUtil.info("admin server start......");
    }

}
