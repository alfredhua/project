package com.common;

import com.common.util.EnvUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
@ComponentScan
public class CommonCore {

    @Resource
    Environment environment;

    @PostConstruct
    public void initEnvironment(){
        EnvUtil.initEnv(environment);
    }


}
