package com.common.mybatis;

import com.common.CommonCore;
import com.common.mybatis.config.MybatisInitConfig;
import com.common.mybatis.intercept.SqlInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

@Import(CommonCore.class)
@DependsOn(value = {"commonCore"})
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
