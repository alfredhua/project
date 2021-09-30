package com.mq;

import com.common.middle.mq.MqTopic;
import com.common.util.GsonUtils;
import com.message.dto.SmsQueueInfo;
import com.message.service.SmsService;
import com.mq.comsume.AbstractMqConsume;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
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
            log.error("短信发送失败:{}",e.getMessage());
        }
    }



}