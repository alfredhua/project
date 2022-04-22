package com.common;

import com.common.util.EnvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class CommonCore {

    @Autowired
    Environment environment;

    @PostConstruct
    public void initEnvironment(){
        EnvUtil.initEnv(environment);
    }

}
