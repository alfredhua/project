package com.common.es.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hua
 * @date 2022/7/17
 */
@Configuration
@Getter
@Setter
@ConditionalOnProperty(prefix = "es",name = "config.enable",havingValue = "true")
@ConfigurationProperties(prefix = "es.config")
public class EsProperties {

    private String clusterNodes;


    private String userName;

    private String password;

}
