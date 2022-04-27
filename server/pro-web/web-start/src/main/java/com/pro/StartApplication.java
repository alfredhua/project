package com.pro;

import com.WebsiteCore;
import com.common.util.LogUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author hua
 */
@SpringBootApplication
@Import({WebCommonCore.class,WebAdminCore.class, WebsiteCore.class,WebMqCore.class,WebCommonCore.class,WebJobCore.class,WebSiteCore.class})
public class StartApplication {

    public static void main(String[] args){
        SpringApplication.run(StartApplication.class, args);
        LogUtil.info("server start......");
    }

}
