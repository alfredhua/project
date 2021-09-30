package com.mq;

import com.common.middle.mq.MqTopic;
import com.common.util.GsonUtils;
import com.common.util.LogUtils;
import com.message.dto.SmsQueueInfo;
import com.message.service.SmsService;
import com.mq.comsume.AbstractMqConsume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsConsume extends AbstractMqConsume<SmsQueueInfo> {

    @Autowired
    SmsService smsService;

    @Override
    public String getChannelName() {
        return MqTopic.SMS;
    }

    @Override
    public SmsQueueInfo transform(String body) {
        return GsonUtils.gson.fromJson(body, SmsQueueInfo.class);
    }

    @Override
    public void consume(SmsQueueInfo smsQueueInfo) {
        try {
            smsService.send(smsQueueInfo.getPhone(), smsQueueInfo.getParams(), smsQueueInfo.getTemplateType());
        }catch (Exception e){
            LogUtils.error("短信发送失败:{}",e.getMessage());
        }
    }



}