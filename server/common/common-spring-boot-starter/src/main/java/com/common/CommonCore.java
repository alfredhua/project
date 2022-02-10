package com.common;

import com.common.dao.intercept.SqlInterceptor;
import com.common.util.EnvUtil;
import org.aspectj.lang.annotation.Pointcut;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.context.ContextLoader;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommonCore {

    @Bean
    public SqlInterceptor initSqlInterceptor(){
        return new SqlInterceptor();
    }

    @Autowired
    Environment environment;

    @PostConstruct
    public void initEnvironment(){
        EnvUtil.initEnv(environment);
    }

    public static void main(String[] args) {
        SpringApplication.run(CommonCore.class, args);
    }


}
