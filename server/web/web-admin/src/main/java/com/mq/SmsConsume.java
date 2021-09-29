package com.mq;

import com.common.util.GsonUtils;
import com.message.dto.SmsQueueInfo;
import com.message.service.SmsService;
import com.mq.comsume.AbstractMqConsume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsConsume extends AbstractMqConsume<SmsQueueInfo> {

    private static final Logger logger = LoggerFactory.getLogger(SmsConsume.class);

    private static final String SMS_TOPIC="SMS";

    @Autowired
    SmsService smsService;

    @Override
    public String getChannelName() {
        return SMS_TOPIC;
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
            logger.error("短信发送失败:{}",e.getMessage());
        }
    }



}