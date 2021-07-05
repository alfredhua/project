package com.message.service;

import com.message.dto.entity.WeChatUserInfo;
import com.message.util.WeChatPublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by guozhenhua
 * date 2020/2/9.
 */
@Component
public class WeChatInfoService {

    @Autowired
    WeChatPublicUtil weChatPublicUtil;

    /**
     * 微信授权链接
     */
    public String generateOAuth2Url(String scope,String redirectUrl,String state) {
        return weChatPublicUtil.generateOAuth2Url(scope,redirectUrl,state);
    }

    /**
     * 获取微信用户信息接口
     */
    public WeChatUserInfo getWeChatUserInfoByCode(String code) {
        return weChatPublicUtil.getUserInfoByOauth2Code(code);
    }


}
