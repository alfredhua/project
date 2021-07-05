package com.message.service.channel;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.common.domain.response.JSONResult;
import com.message.config.SmsAliConfig;
import com.message.constants.SmsChannelEnum;
import com.message.constants.SmsTemplateEnum;
import com.message.dto.SmsInfo;
import com.message.service.template.RegisterTemplate;
import com.message.service.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;

import static com.message.constants.SmsTemplateEnum.REGISTER;

/**
 * @auth guozhenhua
 * @date 2018/11/17
 *
 * 阿里的短信渠道
 */
@Component
public class AliChannel extends BaseChannel {

    @Autowired
    SmsAliConfig smsAliConfig;

    protected static HashMap<SmsTemplateEnum, Template> templateMap=new HashMap<>();

    static {
        templateMap.put(REGISTER,new RegisterTemplate("SMS_125016716"));
    }

    @Override
    protected HashMap<SmsTemplateEnum, Template> getTemplateMap() {
        return templateMap;
    }

    @Override
    public SmsChannelEnum getChannelType() {
        return SmsChannelEnum.ALI;
    }

    @Override
    public JSONResult<Void> post(String phone, String params, SmsInfo smsInfo)throws Exception {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsAliConfig.getAccess_key_id(), smsAliConfig.getAccess_key_secret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", smsAliConfig.getProduct(), smsAliConfig.getUrl());
        IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phone);
        request.setMethod(MethodType.POST);
        request.setSignName(smsAliConfig.getPrefix());
        request.setTemplateCode(smsInfo.getCode());
        request.setTemplateParam(params);
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (!ObjectUtils.isEmpty(sendSmsResponse) && "OK".equals(sendSmsResponse.getCode())) {
            return JSONResult.success();
        }
        return JSONResult.error(sendSmsResponse.getCode(),sendSmsResponse.getMessage());
    }


}
