package com.common.zk;

import com.common.CommonCore;
import com.common.zk.config.ZkConfig;
import com.common.zk.enity.ZkProperties;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@Configuration
@Import(CommonCore.class)
@DependsOn(value = {"commonCore"})
public class ZkCore {

    @Autowired
    ZkProperties zkProperties;

    @Bean(initMethod = "init")
    @ConditionalOnProperty(prefix = "zk",name = "config.enable",havingValue = "true")
    public ZkConfig init(){
        return new ZkConfig(zkProperties);
    }

}
