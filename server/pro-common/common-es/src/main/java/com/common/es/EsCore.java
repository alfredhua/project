package com.common.es;

import com.common.CommonCore;
import com.common.es.config.EsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

@Import(CommonCore.class)
@DependsOn(value = {"commonCore"})
public class EsCore {

    @Bean(initMethod = "init")
    public EsConfig esConfig(){
        return new EsConfig();
    }
}
