package com.message.service.template;

import com.message.dto.SmsInfo;

import java.util.Map;

public abstract class Template {

     String code;

     public Template(String code) {
          this.code = code;
     }

     public abstract SmsInfo parse(Map<String,String> map);


}