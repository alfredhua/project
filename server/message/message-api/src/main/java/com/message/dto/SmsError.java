package com.message.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsError {

  private String code;

  private String msg;

  public SmsError(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public static SmsError setError(String code, String msg){
    return new SmsError(code,msg);
  }
}
