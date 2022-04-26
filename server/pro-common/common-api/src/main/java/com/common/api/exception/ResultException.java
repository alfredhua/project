package com.common.api.exception;

import com.common.api.constants.SysErrorCodeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultException extends Exception{

    String code;

    String msg;

    private ResultException(String code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public static ResultException error(SysErrorCodeEnum sysErrorCodeEnum){
        return new ResultException(sysErrorCodeEnum.getCode(),sysErrorCodeEnum.getMsg());
    }

    public static ResultException error(String code, String msg) {
        return new ResultException(code,msg);
    }
}
