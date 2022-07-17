package com.common.es;

import com.common.CommonCore;
import com.common.es.config.EsConfig;
import com.common.es.config.EsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

@Import(CommonCore.class)
@DependsOn(value = {"commonCore"})
@ConditionalOnProperty(prefix = "es",name = "config.enable",havingValue = "true")
public class EsCore {

    @Autowired
    EsProperties esProperties;

    @Bean(initMethod = "init")
    public EsConfig esConfig(){
        return new EsConfig(esProperties);
    }

}
