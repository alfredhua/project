package com.message.api;

import com.common.domain.response.JSONResult;
import com.message.constants.SmsTemplateEnum;

import java.util.List;
import java.util.Map;

/**
 * @auth guozhenhua
 * @date 2018/11/17
 */
public interface SmsRpcService {

    /**
     * 短信发送，单条
     * @param phone
     * @param params
     * @param templateType
     * @return
     * @throws Exception
     */
    JSONResult send(String phone, Map<String,String> params, SmsTemplateEnum templateType) throws Exception;

    /**
     * 多条
     * @param phoneList
     * @param params
     * @param templateType
     * @return
     * @throws Exception
     */
    JSONResult send(List<String> phoneList, Map<String,String> params, SmsTemplateEnum templateType) throws Exception;

}
