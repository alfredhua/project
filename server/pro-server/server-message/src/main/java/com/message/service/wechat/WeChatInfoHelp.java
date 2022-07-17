package com.message.service.wechat;

import com.message.dao.entity.WeChatUserInfo;
import org.springframework.stereotype.Component;

/**
 * Created by guozhenhua
 * date 2020/2/9.
 */
@Component
public class WeChatInfoHelp {

    /**
     * 微信授权链接
     */
    public String generateOAuth2Url(String scope,String redirectUrl,String state) {
        return WeChatPublicService.generateOAuth2Url(scope,redirectUrl,state);
    }

    /**
     * 获取微信用户信息接口
     */
    public WeChatUserInfo getWeChatUserInfoByCode(String code) {
        return WeChatPublicService.getUserInfoByOauth2Code(code);
    }
//

}
