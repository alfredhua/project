package com.pro.mq;

import com.common.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsConsume {

//    @Autowired
//    SmsService smsService;
//
//    @Override
//    public String getTopic() {
//        return "SMS";
//    }
//
//    @Override
//    public void consume(SmsQueueInfo smsQueueInfo) {
//        try {
//            smsService.send(smsQueueInfo.getPhone(), smsQueueInfo.getParams(), smsQueueInfo.getTemplateType());
//        }catch (Exception e){
//            LogUtil.error("短信发送失败:{}",e.getMessage());
//        }
//    }



}