package com.message.constants;

import lombok.Getter;

/**
 * Created by guozhenhua
 * date 2020/3/20.
 */
@Getter
public enum WeChatSendTypeEnum {

    ACTIVITY("ACTIVITY","活动");

    String  Type;

    String name;

    WeChatSendTypeEnum(String type, String name) {
        Type = type;
        this.name = name;
    }


}
