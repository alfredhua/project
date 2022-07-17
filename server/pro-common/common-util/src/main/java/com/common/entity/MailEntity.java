package com.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hua
 */
@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "mail.config")
public class MailEntity {

    private String emailName;

    private String password;

    private String port;

    private String toMail;

}
