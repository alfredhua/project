package com.message.service.sms;

import com.message.service.sms.channel.AliChannel;
import com.pro.api.entity.message.constants.SmsTemplateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author guozhenhua
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
