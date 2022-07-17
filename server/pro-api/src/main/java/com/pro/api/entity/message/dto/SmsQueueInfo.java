package com.pro.api.entity.message.dto;

import com.pro.api.entity.message.constants.SmsTemplateEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * @auth guozhenhua
 * @date 2018/11/19
 */
@Getter
@Setter
public class SmsQueueInfo implements Serializable {

    private String phone;

    private SmsTemplateEnum templateType;

    private Map<String,String> params;

    public SmsQueueInfo() {
    }

    public SmsQueueInfo(String phone, Map<String, String> params, SmsTemplateEnum templateType) {
        this.phone = phone;
        this.templateType = templateType;
        this.params = params;
    }

    @Override
    public String toString() {
        return "SmsQueueInfo{" + "phoneList=" + phone + ", templateType=" + templateType + ", params=" + params + '}';
    }
}
