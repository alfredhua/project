package com.common.mybatis;

import com.common.CommonCore;
import com.common.mybatis.config.MybatisConfig;
import com.common.mybatis.config.TypeHandlerConfig;
import com.common.mybatis.intercept.SqlInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(CommonCore.class)
public class MybatisCore {

    @Bean(initMethod = "init")
    public MybatisConfig mybatisConfig(){
        return new MybatisConfig();
    }

    @Bean
    public TypeHandlerConfig typeHandlerConfig(){
        return new TypeHandlerConfig();
    }

    @Bean
    public SqlInterceptor sqlInterceptor(){
        return new SqlInterceptor();
    }

}
