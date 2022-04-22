package com.common.api.entity.response;

import com.common.api.constants.SysErrorCodeEnum;
import lombok.Data;

/**
 * @author hua
 */
@Data
public class ResultResponse<T> implements java.io.Serializable {


    public static String SUCCESS="SUCCESS";

    private static String FAIL="FAIL";


    private String msg;

    private  String code;

    private T data;

    public ResultResponse() {
        this.code=SUCCESS;
        this.msg="请求成功";
        this.data=null;
    }

    public ResultResponse(T data) {
        this.code=SUCCESS;
        this.msg="请求成功";
        this.data = data;
    }

    public ResultResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data=null;
    }

    public static<T> ResultResponse<T> success() {
        return new ResultResponse<>();
    }

    public static ResultResponse<Void> result(boolean result) {
        ResultResponse<Void> jsonResult = new ResultResponse<>();
        jsonResult.setCode(result?SUCCESS:FAIL);
        return jsonResult;
    }


    public static<T> ResultResponse<T> success(T data) {
        return new ResultResponse<>(data);
    }

    public static<T> ResultResponse<T> error(String code, String msg){
        return new ResultResponse<>(code,msg);
    }

    public static<T> ResultResponse<T> errorSysError(SysErrorCodeEnum sysErrorCodeEnum){
        return new ResultResponse<T>(sysErrorCodeEnum.getCode(),sysErrorCodeEnum.getMsg());
    }

    public static<T> ResultResponse<T> saveError(){
        return errorSysError(SysErrorCodeEnum.SAVE_ERROR);
    }

    public static<T> ResultResponse<T> getError(){
        return errorSysError(SysErrorCodeEnum.GET_ERROR);
    }

    public static<T> ResultResponse<T> delError(){
        return errorSysError(SysErrorCodeEnum.DEL_ERROR) ;
    }

    public static<T> ResultResponse<T> fail(){
        return errorSysError(SysErrorCodeEnum.FAIL);
    }

}
