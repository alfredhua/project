package com.message.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author guozhenhua
 * @date 2019/01/14
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WeiXinConfig {

    /**
     * 公众号的AppId
     */
    private String public_app_id;

    /**
     * 公众号的AppSecret
     */
    private String public_app_secret;

    /**
     * 重定向url
     */
    private String redirect_url;

    /**
     * 小程序的appId;
     */
    private String xcx_app_id;

    /**
     * 小程序的xcxAppSecret
     */
    private String xcx_app_secret;


    @PostConstruct
    public void initWeChatPublicUtil(){
//        WeChatPublicUtil.initAppInfo(public_app_id,public_app_secret);
    }


}
