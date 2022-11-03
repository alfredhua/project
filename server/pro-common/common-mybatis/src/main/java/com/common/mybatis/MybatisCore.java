package com.common.mybatis;

import com.common.mybatis.config.MybatisInitConfig;
import com.common.mybatis.intercept.SqlInterceptor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(value = "com.common.mybatis.*,com.test.mybatis.*")
public class MybatisCore {

    @Bean(initMethod = "init")
    public MybatisInitConfig mybatisInitConfig(){
        return new MybatisInitConfig();
    }

    @Bean
    public SqlInterceptor sqlInterceptor(){
        return new SqlInterceptor();
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

}
