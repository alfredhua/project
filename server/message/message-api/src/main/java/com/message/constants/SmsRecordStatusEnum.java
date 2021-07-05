package com.message.constants;

import lombok.Getter;

/**
 * @auth guozhenhua
 * @date 2018/11/19
 */
@Getter
public enum  SmsRecordStatusEnum {

    INIT((short)1,"初始状态"),
    SUCCESS((short)2,"成功状态"),
    FAIL((short)3,"失败");


    SmsRecordStatusEnum(short status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private short status;

    private String msg;


}
