package com.message.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "sms.ali")
public class SmsAliConfig {

    private String url;
    private String product;
    private String prefix;
    private String region_id;
    private String end_point_name;
    private String access_key_id;
    private String access_key_secret;

}
