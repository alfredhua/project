package com.admin.config;

import com.common.redis.RedisUtils;
import com.admin.interceptor.AuthDataInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthDataConfig {

    @Autowired
    RedisUtils redisUtils;

    @Bean
    public AuthDataInterceptor authDataInterceptor(){
        return new AuthDataInterceptor(redisUtils);
    }
}
