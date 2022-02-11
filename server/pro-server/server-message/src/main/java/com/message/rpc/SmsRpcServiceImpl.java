package com.message.rpc;

import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.response.JSONResult;
import com.common.util.ValidateUtil;
import com.pro.message.SmsRpcService;
import com.pro.message.constants.SmsTemplateEnum;
import com.pro.message.dto.SmsQueueInfo;
import org.apache.dubbo.config.annotation.Service;
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

    @Override
    public JSONResult<Void>  send(String phone, Map<String, String> params, SmsTemplateEnum templateType) throws Exception {
        try {
            SmsQueueInfo smsQueueInfo = new SmsQueueInfo();
            smsQueueInfo.setParams(params);
            smsQueueInfo.setPhone(phone);
            smsQueueInfo.setTemplateType(templateType);
//            MqSendClientUtil.send(MqTopic.SMS, smsQueueInfo);
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
//                MqClientUtil.send(MqTopic.SMS, smsQueueInfo);
            }
        });
        return JSONResult.success();
    }



}
