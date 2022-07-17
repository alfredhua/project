package com.pro.mq;

import com.common.rabbitmq.consumer.AbstractMqConsumer;
import com.common.util.LogUtil;
import com.message.service.sms.SmsService;
import com.pro.api.entity.message.dto.SmsQueueInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hua
 */
@Component
public class SmsConsume extends AbstractMqConsumer<SmsQueueInfo> {

    @Autowired
    SmsService smsService;

    @Override
    public String getTopic() {
        return "SMS";
    }

    @Override
    public String consume(SmsQueueInfo smsQueueInfo) {
        try {
            smsService.send(smsQueueInfo.getPhone(), smsQueueInfo.getParams(), smsQueueInfo.getTemplateType());
        }catch (Exception e){
            LogUtil.error("短信发送失败:{}",e.getMessage());
            return "FALSE";
        }
        return "SUCCESS";
    }



}