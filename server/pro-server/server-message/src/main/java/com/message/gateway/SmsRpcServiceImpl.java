package com.message.gateway;

import com.common.api.entity.response.ResultResponse;
import com.common.rabbitmq.client.MqClient;
import com.common.util.GsonUtil;
import com.common.util.LogUtil;
import com.pro.api.entity.message.SmsRpcService;
import com.pro.api.entity.message.constants.SmsTemplateEnum;
import com.pro.api.entity.message.dto.SmsQueueInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;
import java.util.Map;

/**
 * @author hua
 * @date 2022/7/17
 */
@Slf4j
@DubboService
public class SmsRpcServiceImpl implements SmsRpcService {

    @Override
    public ResultResponse<Boolean> send(String phone, Map<String, String> params, String templateType) throws Exception {
        SmsTemplateEnum type = SmsTemplateEnum.getByType(templateType);
        if (ObjectUtils.isEmpty(type)){
            return ResultResponse.error("404","模板类型不存在");
        }
        SmsQueueInfo smsQueueInfo = new SmsQueueInfo();
        smsQueueInfo.setParams(params);
        smsQueueInfo.setPhone(phone);
        smsQueueInfo.setTemplateType(type);
        MqClient.send("SMS",smsQueueInfo);
        return ResultResponse.success(true);
    }

    @Override
    public ResultResponse<Boolean> send(List<String> phoneList, Map<String, String> params, String templateType){
        phoneList.forEach(item->{
            try {
                this.send(item,params,templateType);
            } catch (Exception e) {
                LogUtil.error("信息发送失败:{}",e);
                LogUtil.error("信息参数:{},{},{}", GsonUtil.toJSON(phoneList),GsonUtil.toJSON(params),templateType);
            }
        });
        return ResultResponse.success(true);
    }
}
