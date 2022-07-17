package com.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author hua
 */
@Configuration
@Getter
@Setter
@ConditionalOnProperty(value = "mail.config.enable",havingValue = "true")
public class MailEntity {

    @Value("mail.config.emailName")
    private String emailName;

    @Value("mail.config.password")
    private String password;

    @Value("mail.smtp.port")
    private String port;

    @Value("mail.config.toMail")
    private String toMail;
}
