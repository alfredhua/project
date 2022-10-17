package com.common.config;

import com.common.entity.MailEntity;
import com.common.util.LogUtil;
import com.common.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "mail",name = "config.enable",havingValue = "true")
public class MailConfig {

    @Resource
    MailEntity mailEntity;

    @PostConstruct
    public void initMail(){
        MailUtil.initMail(mailEntity);
        LogUtil.info("init mail success");
    }

}
