package com.common.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.LocalDateTimeTypeHandler;
import org.apache.ibatis.type.LocalDateTypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class TypeHandlerConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        SqlSessionFactory sqlSessionFactory = applicationContext.getBean(SqlSessionFactory.class);
//        TypeHandlerRegistry typeHandlerRegistry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
//        typeHandlerRegistry.register(LocalDateTime.class, LocalDateTimeTypeHandler.class);
//        typeHandlerRegistry.register(LocalDate.class, LocalDateTypeHandler.class);
    }

}
