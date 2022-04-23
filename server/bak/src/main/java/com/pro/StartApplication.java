package com.pro;

import com.WebsiteCore;
import com.auth.AuthCore;
import com.common.util.LogUtil;
import com.develop.DevelopCore;
import com.message.MessageCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hua
 */
@EnableScheduling
@SpringBootApplication
@Import({AuthCore.class, MessageCore.class, DevelopCore.class, WebsiteCore.class})
public class StartApplication {

    public static void main(String[] args){
        SpringApplication.run(StartApplication.class, args);
        LogUtil.info("admin server start......");
    }

}