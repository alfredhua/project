package com.common.rabbitmq.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "mq.config")
public class RabbitMqProperties {

    private String userName;

    private String password;

    private String virtualHost;

    private String host;

    private String port;

    private String  model;

}
