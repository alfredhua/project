package com.message.service;

import com.message.constants.SmsTemplateEnum;
import com.message.service.channel.AliChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @auth guozhenhua
 * @date 2018/11/17
 */
@Component
public class SmsService {

    @Autowired
    AliChannel aliChannel;

    public boolean send(String phone, Map<String, String> params, SmsTemplateEnum templateType) throws Exception {
       return aliChannel.send(phone, params,templateType);
    }

    public boolean  send(List<String> phoneList, Map<String, String> params, SmsTemplateEnum templateType) throws Exception {
        return  aliChannel.send(phoneList, params,templateType);
    }

}
