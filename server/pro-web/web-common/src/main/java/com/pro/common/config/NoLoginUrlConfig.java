package com.pro.common.config;

import lombok.Getter;

import java.util.Arrays;
import java.util.regex.Pattern;

@Getter
public enum  NoLoginUrlConfig {


  LOGIN("/login"),
  DOCS("/docs.html"),
  WEBJARS("/webjars/*"),
  API_DOCS("/v2/api-docs"),
  SWAGGER_UI("/swagger-*"),
  SPRINGFOX("/springfox*"),

  SHUTDOWN("/actuator/shutdown"),
  SAVE_CAPTCHA("/admin/common/save-captcha"),

  DRUID("/druid/*"),

  ;
  String url;
  NoLoginUrlConfig(String url) {
    this.url = url;
  }


  public static boolean containURI(String requestURI){
    final long count = Arrays.stream(values())
            .filter(item -> Pattern.matches(item.getUrl().replaceAll("\\*", ".*").replaceAll("/+", "/"), requestURI))
            .count();
    return count>0;
  }

}