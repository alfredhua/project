package com.admin.config;

import com.admin.interceptor.AuthDataInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthDataConfig {

    @Bean
    public AuthDataInterceptor authDataInterceptor(){
        return new AuthDataInterceptor();
    }
}
