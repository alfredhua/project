package com.pro;

import com.WebsiteCore;
import com.common.util.LogUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hua
 */
@SpringBootApplication
@Import({WebAdminCore.class, WebsiteCore.class,WebMqCore.class,WebCommonCore.class,WebJobCore.class,WebSiteCore.class})
public class StartApplication {

    public static void main(String[] args){
        SpringApplication.run(WebAdminCore.class, args);
        LogUtil.info("admin server start......");
    }

}
