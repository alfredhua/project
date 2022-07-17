package com.common.zk.enity;

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
@ConfigurationProperties(prefix = "zk.config")
public class ZkProperties {

    private String url;

}
