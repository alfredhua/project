package com.message.service;

import com.message.dto.entity.WeChatUserInfo;
import com.message.util.WeChatPublicUtil;
import org.springframework.stereotype.Component;

/**
 * Created by guozhenhua
 * date 2020/2/9.
 */
@Component
public class WeChatInfoService {

    /**
     * 微信授权链接
     */
    public String generateOAuth2Url(String scope,String redirectUrl,String state) {
        return WeChatPublicUtil.generateOAuth2Url(scope,redirectUrl,state);
    }

    /**
     * 获取微信用户信息接口
     */
    public WeChatUserInfo getWeChatUserInfoByCode(String code) {
        return WeChatPublicUtil.getUserInfoByOauth2Code(code);
    }


}
