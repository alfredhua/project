package com.website.constant;

import lombok.Getter;

@Getter
public enum WebSiteDictionaryEnum {


  NEWS("'NEWS'","新闻动态"),
  ABOUT_US("'ABOUT_US'","关于我们"),
  CONTACT_US("'CONTACT_US'","联系我们"),
  COMPANY("COMPANY","企业文化"),
  WANT("WANT","公司愿景"),
  DEVELOP("DEVELOP","发展历程"),

  ;

  String type;
  String msg;


  WebSiteDictionaryEnum(String type, String msg) {
    this.type = type;
    this.msg = msg;
  }



}
