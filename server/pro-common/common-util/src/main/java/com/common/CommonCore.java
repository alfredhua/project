package com.common;

import com.common.entity.MailEntity;
import com.common.util.EnvUtil;
import com.common.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class CommonCore {

    @Autowired
    Environment environment;

    @Autowired(required = false)
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
