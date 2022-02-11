package com.mq;

//import com.common.middle.mq.MqTopic;
import com.common.util.GsonUtil;
import com.common.util.LogUtil;
import com.message.service.SmsService;
import com.mq.comsume.AbstractMqConsume;
import com.pro.message.dto.SmsQueueInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsConsume extends AbstractMqConsume<SmsQueueInfo> {

    @Autowired
    SmsService smsService;

    @Override
    public String getChannelName() {
//        return MqTopic.SMS;
        return null;
    }

    @Override
    public SmsQueueInfo transform(String body) {
        return GsonUtil.gson.fromJson(body, SmsQueueInfo.class);
    }

    @Override
    public void consume(SmsQueueInfo smsQueueInfo) {
        try {
            smsService.send(smsQueueInfo.getPhone(), smsQueueInfo.getParams(), smsQueueInfo.getTemplateType());
        }catch (Exception e){
            LogUtil.error("短信发送失败:{}",e.getMessage());
        }
    }



}