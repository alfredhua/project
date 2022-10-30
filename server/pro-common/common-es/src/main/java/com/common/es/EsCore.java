package com.common.es;

import com.common.es.config.EsConfig;
import com.common.es.config.EsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
@ConditionalOnProperty(prefix = "es",name = "config.enable",havingValue = "true")
public class EsCore {

    @Autowired
    EsProperties esProperties;
    @Bean(initMethod = "init")
    public EsConfig esConfig(){
        return new EsConfig(esProperties);
    }

}
