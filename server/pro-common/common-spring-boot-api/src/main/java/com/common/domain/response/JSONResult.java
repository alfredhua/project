package com.common.domain.response;

import com.common.domain.constants.SysErrorCodeEnum;
import lombok.Data;

/**
 * @author hua
 */
@Data
public class JSONResult<T> implements java.io.Serializable {


    public static String SUCCESS="SUCCESS";

    private static String FAIL="FAIL";


    private String msg;

    private  String code;

    private T data;

    public JSONResult() {
        this.code=SUCCESS;
        this.msg="请求成功";
        this.data=null;
    }

    public JSONResult(T data) {
        this.code=SUCCESS;
        this.msg="请求成功";
        this.data = data;
    }

    public JSONResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data=null;
    }

    public static<T> JSONResult<T> success() {
        return new JSONResult<>();
    }

    public static JSONResult<Void> result(boolean result) {
        JSONResult<Void>  jsonResult = new JSONResult<>();
        jsonResult.setCode(result?SUCCESS:FAIL);
        return jsonResult;
    }


    public static<T> JSONResult<T> success(T data) {
        return new JSONResult<>(data);
    }

    public static<T> JSONResult<T> error(String code, String msg){
        return new JSONResult<>(code,msg);
    }

    public static<T> JSONResult<T> errorSysError(SysErrorCodeEnum sysErrorCodeEnum){
        return new JSONResult<T>(sysErrorCodeEnum.getCode(),sysErrorCodeEnum.getMsg());
    }

    public static<T> JSONResult<T> saveError(){
        return errorSysError(SysErrorCodeEnum.SAVE_ERROR);
    }

    public static<T> JSONResult<T> getError(){
        return errorSysError(SysErrorCodeEnum.GET_ERROR);
    }

    public static<T> JSONResult<T> delError(){
        return errorSysError(SysErrorCodeEnum.DEL_ERROR) ;
    }

    public static<T> JSONResult<T> fail(){
        return errorSysError(SysErrorCodeEnum.FAIL);
    }

}
