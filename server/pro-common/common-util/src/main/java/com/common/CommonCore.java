package com.common;

import com.common.entity.MailEntity;
import com.common.util.EnvUtil;
import com.common.util.MailUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
@ComponentScan
public class CommonCore {

    @Resource
    Environment environment;

    @Resource
    MailEntity mailEntity;

    @PostConstruct
    public void initEnvironment(){
        EnvUtil.initEnv(environment);
    }

    @PostConstruct
    @ConditionalOnProperty(prefix = "mail",name = "config.enable",havingValue = "true")
    public void initMail(){
        MailUtil.initMail(mailEntity);
    }
}
