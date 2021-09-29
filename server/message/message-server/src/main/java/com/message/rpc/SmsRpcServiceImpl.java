package com.message.rpc;

import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.response.JSONResult;
import com.common.middle.mq.MqSendClientUtil;
import com.common.util.ValidateUtil;
import com.message.api.SmsRpcService;
import com.message.constants.SmsTemplateEnum;
import com.message.dto.SmsQueueInfo;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @auth guozhenhua
 * @date 2018/11/17
 */
@Component
@Service(interfaceClass = SmsRpcService.class)
public class SmsRpcServiceImpl implements SmsRpcService {

    private static final Logger logger = LoggerFactory.getLogger(SmsRpcServiceImpl.class);

    private static String SMS_TOPIC="SMS";

    @Override
    public JSONResult<Void>  send(String phone, Map<String, String> params, SmsTemplateEnum templateType) throws Exception {
        try {
            SmsQueueInfo smsQueueInfo = new SmsQueueInfo();
            smsQueueInfo.setParams(params);
            smsQueueInfo.setPhone(phone);
            smsQueueInfo.setTemplateType(templateType);
            MqSendClientUtil.send(SMS_TOPIC, smsQueueInfo);
        }catch (Exception e){
            throw new RuntimeException("短信发送失败",e);
        }
        return JSONResult.success();
    }

    @Override
    public JSONResult<Void>  send(List<String> phoneList, Map<String, String> params, SmsTemplateEnum templateType) throws Exception {
        if (phoneList.isEmpty()){
            return JSONResult.errorSysError(SysErrorCodeEnum.PHONE_EMPTY);
        }
        phoneList.forEach(phone->{
            if (ValidateUtil.isValidPhone(phone)){
                SmsQueueInfo smsQueueInfo=new SmsQueueInfo();
                smsQueueInfo.setParams(params);
                smsQueueInfo.setPhone(phone);
                smsQueueInfo.setTemplateType(templateType);
                MqSendClientUtil.send(SMS_TOPIC, smsQueueInfo);
            }
        });
        return JSONResult.success();
    }



}
