package com.pro.api.entity.message.constants;

import lombok.Getter;

/**
 * @auth guozhenhua
 * @date 2018/11/17
 */
@Getter
public enum SmsTemplateEnum {

    /**
     * 注册
     */
    REGISTER,
    ;


    public static SmsTemplateEnum getByType(String type){
        SmsTemplateEnum[] values = values();
        for (SmsTemplateEnum smsTemplateEnum:values){
            if (smsTemplateEnum.name().equals(type)){
                return smsTemplateEnum;
            }
        }
        return null;
    }
}
