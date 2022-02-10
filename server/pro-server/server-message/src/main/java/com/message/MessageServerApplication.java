package com.message;

import com.common.util.LogUtil;
import org.springframework.boot.SpringApplication;

/**
 * @author guozhenhua
 * @date 2020/04/16
 */
public class MessageServerApplication {


    public static void main(String[] args){
        SpringApplication.run(MessageCore.class,args);
        LogUtil.info("message server  start......");
    }
}
