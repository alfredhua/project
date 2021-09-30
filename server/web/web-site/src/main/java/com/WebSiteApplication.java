package com;

import com.common.util.LogUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hua
 */
@SpringBootApplication
public class  WebSiteApplication {


    public static void main(String[] args){
        SpringApplication.run(WebSiteApplication.class, args);
        LogUtils.info("site server start......");
    }

}
