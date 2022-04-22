package com.common.zk;

import com.common.CommonCore;
import com.common.zk.config.ZkConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CommonCore.class)
public class ZkCore {

    @Bean(initMethod = "init")
    public ZkConfig init(){
        return new ZkConfig();
    }

}
