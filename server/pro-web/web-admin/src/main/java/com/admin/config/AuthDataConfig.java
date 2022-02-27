package com.admin.config;

import com.admin.web.interceptor.AuthDataInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthDataConfig {

    @Bean
    public AuthDataInterceptor authDataInterceptor(){
        return new AuthDataInterceptor();
    }
}
