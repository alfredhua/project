package com.common.middle.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailConfigProperties {

  /**
   *端口
   */
  private String port;

  /**
   * 阿里云邮箱登录名
   */
  private String email_name;
  /**
   * 阿里云邮箱密码
   */
  private String email_password;
  /**
   * 主机地址
   */
  private String mail_host;

  /**
   * 被发送邮箱地址
   */
  private String to_mail;

}
