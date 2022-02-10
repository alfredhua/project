package com.common.domain.constants;

import lombok.Getter;

@Getter
public enum SysErrorCodeEnum {

    SAVE_ERROR("SAVE_ERROR","保存失败"),
    GET_ERROR("GET_ERROR","获取失败"),
    DEL_ERROR("DEL_ERROR","删除失败"),
    FAIL("FAIL","请求失败"),

    DELETE_ERROR("DELETE_ERROR","删除失败"),
    UPDATE_ERROR("UPDATE_ERROR","更新失败"),
    PARAMS_ERROR("PARAMS_ERROR", "参数错误"),
    NONE("NONE_ERROR", "未知错误"),
    ERR_SYSTEM("ERR_SYSTEM", "系统错误"),
    ERR_SERVICE_STOP("ERR_SERVICE_STOP", "服务不可用"),
    METHOD_ERROR("METHOD_ERROR", "方法请求错误"),
    ERR_AUTH_LIMIT("ERR_AUTH_LIMIT", "访问权限受限"),
    ERR_ILLEGAL_PARAM("ERR_ILLEGAL_PARAM", "参数值非法"),
    ERR_REST_FAIL("ERR_REST_FAIL", "服务接口调用失败"),
    CODE_ERROR("CODE_ERROR", "code错误"),
    TOKEN_NOT_FOUND("TOKEN_NOT_FOUND", "token不存在"),
    TOKEN_INVALID("TOKEN_INVALID", "token无效"),
    SIGN_NULL("SIGN_NULL", "签名为空"),
    SIGN_ERROR("SIGN_ERROR", "签名错误"),
    TOKEN_IS_NULL("TOKEN_IS_NULL", "token为空"),
    NULL_ERROR("NUL_ERROR","用户名或密码为空"),
    USER_NAME_ERROR("NAME_ERROR","用户名不存在"),
    FROZEN("FROZEN","账户已冻结"),
    VERIFY_ERROR("VERIFY_ERROR","验证码错误"),
    PASS_WORD_ERROR("PASS_WORD_ERROR","密码错误"),

    EMPTY("EMPTY","数据不存在"),

    EMAIL_ERROR("EMAIL_ERROR","邮箱已存在"),
    PHONE_ERROR("PHONE_ERROR","手机号已存在"),
    PHONE_EMPTY("PHONE_EMPTY","手机号为空"),
    OLD_PASSWORD_ERROR("OLD_PASSWORD_ERROR","旧密码错误"),
    RESET_PASSWORD_ERROR("RESET_PASSWORD_ERROR","重置密码错误"),
    UPDATE_PASSWORD_ERROR("UPDATE_PASSWORD_ERROR","更新密码错误"),
    CONFIRM_PASSWORD_ERROR("CONFIRM_PASSWORD_ERROR","俩次密码不一致"),
    USER_NAME_EXIST_ERROR("USER_NAME_EXIST_ERROR","用户名已存在"),


    ;

    private String code;
    private String msg;

    SysErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }




}
