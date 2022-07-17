package com.pro.api.entity.message;

import com.common.api.entity.response.ResultResponse;

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
    ResultResponse<Boolean> send(String phone, Map<String,String> params, String templateType) throws Exception;

    /**
     * 多条
     * @param phoneList
     * @param params
     * @param templateType
     * @return
     * @throws Exception
     */
    ResultResponse<Boolean> send(List<String> phoneList, Map<String,String> params, String templateType);

}
