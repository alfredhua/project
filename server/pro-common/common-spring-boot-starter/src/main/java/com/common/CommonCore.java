package com.common;

import com.common.util.EnvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@EnableAutoConfiguration
@ComponentScan
public class CommonCore {

    @Autowired
    Environment environment;

    @PostConstruct
    public void initEnvironment(){
        EnvUtil.initEnv(environment);
    }

}
