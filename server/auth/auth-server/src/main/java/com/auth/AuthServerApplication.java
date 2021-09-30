package com.auth;

import com.common.util.LogUtils;
import org.springframework.boot.SpringApplication;

/**
 * @author guozhenhua
 * @date 2020/04/16
 */
public class AuthServerApplication {

    public static void main(String[] args){

        SpringApplication.run(AuthCore.class,args);

        LogUtils.info("auth server  start......");

    }
}
