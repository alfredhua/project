package com.message.service.sms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @auth guozhenhua
 * @date 2018/11/19
 */
@Getter
@Setter
@AllArgsConstructor
public class SmsInfo {

    /**
     * 渠道相应的模板编码
     */
    private String code;

    /**
     * 短信内容
     */
    private String content;

}
