package com.common.es;

import com.common.CommonCore;
import com.common.es.config.EsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(CommonCore.class)
public class EsCore {

    @Bean(initMethod = "init")
    public EsConfig esConfig(){
        return new EsConfii();
    }
}
