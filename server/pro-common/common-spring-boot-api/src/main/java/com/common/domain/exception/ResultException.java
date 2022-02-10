package com.common.domain.exception;

import com.common.domain.constants.SysErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultException extends Exception{

    private String msg;

    private  String code;

    public ResultException() {
    }

    public ResultException(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public static  ResultException error(SysErrorCodeEnum errorEnum){
        return new ResultException(errorEnum.getCode(),errorEnum.getMsg());
    }

    public static  ResultException error(String code,String msg){
        return new ResultException(code,msg);
    }
}
