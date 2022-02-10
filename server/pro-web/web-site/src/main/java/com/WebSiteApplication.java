package com;

import com.common.util.LogUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hua
 */
@SpringBootApplication
public class  WebSiteApplication {


    public static void main(String[] args){
        SpringApplication.run(WebSiteApplication.class, args);
        LogUtil.info("site server start......");
    }

}
