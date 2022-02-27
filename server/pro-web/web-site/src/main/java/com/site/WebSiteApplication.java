package com.site;

import com.WebsiteCore;
import com.auth.AuthCore;
import com.common.util.LogUtil;
import com.message.MessageCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author hua
 */
@SpringBootApplication
@Import({AuthCore.class, MessageCore.class, WebsiteCore.class})
public class  WebSiteApplication {


    public static void main(String[] args){
        SpringApplication.run(WebSiteApplication.class, args);
        LogUtil.info("site server start......");
    }

}
