package com.auth.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = { "com.auth.dao"},sqlSessionFactoryRef = "authSqlSessionFactory")
public class DataSourceConfig {

    @Bean("authDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.auth") //读取application.yml中的配置参数映射成为一个对象
    public DataSource getDb1DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("authSqlSessionFactory")
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("authDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // mapper的xml形式文件位置必须要配置，不然将报错：no statement （这种错误也可能是mapper的xml中，namespace与项目的路径不一致导致）
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/db1/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean("authSqlSessionTemplate")
    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}