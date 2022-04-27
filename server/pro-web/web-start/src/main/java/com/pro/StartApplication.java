package com.pro;

import com.common.util.LogUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author hua
 */
@SpringBootApplication
@Import({WebCommonCore.class,WebAdminCore.class, WebSiteInfoCore.class,WebMqCore.class,WebCommonCore.class,WebJobCore.class, WebSiteInfoCore.class})
public class StartApplication {

    public static void main(String[] args){
        SpringApplication.run(StartApplication.class, args);
        LogUtil.info("server start......");
    }

}
