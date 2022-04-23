package com.common.redis;


import com.common.CommonCore;
import com.common.redis.config.RedisConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CommonCore.class)
@DependsOn(value = {"commonCore"})
public class RedisCore {

    @Bean(initMethod = "init")
    public RedisConfig initEnvironment(){
        return new RedisConfig();
    }

}
