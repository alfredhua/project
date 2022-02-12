package com.common;

import com.common.middle.redis.RedisUtil;
import com.common.util.EnvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import javax.annotation.PostConstruct;

@EnableAutoConfiguration
@ComponentScan
public class CommonCore {

    @Autowired
    Environment environment;

    @Autowired
    RedisConnectionFactory connectionFactory;

    @PostConstruct
    public void initEnvironment(){
        EnvUtil.initEnv(environment);
        RedisUtil.initRedisTemplate(connectionFactory);
    }

}
