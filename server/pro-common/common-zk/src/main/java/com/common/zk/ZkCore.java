package com.common.zk;

import com.common.CommonCore;
import com.common.zk.config.ZkConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@Configuration
@Import(CommonCore.class)
@DependsOn(value = {"commonCore"})
public class ZkCore {

    @Bean(initMethod = "init")
    public ZkConfig init(){
        return new ZkConfig();
    }

}
