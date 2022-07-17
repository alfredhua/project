package com.message.service.sms.template;

import com.message.service.sms.domain.SmsInfo;

import java.util.Map;

public abstract class Template {

     String code;

     public Template(String code) {
          this.code = code;
     }

     public abstract SmsInfo parse(Map<String,String> map);


}