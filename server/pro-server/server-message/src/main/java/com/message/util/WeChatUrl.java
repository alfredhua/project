package com.message.util;

/**
 * @author guozhenhua
 * @date 2019/01/14
 */
public class WeChatUrl {

    /**
     * 获取access_token
     */
    public static String ACCESS_TOKEN="https://api.wechat.qq.com/cgi-bin/token";

    /**
     * 获取Ip地址
     */
    public static String WEI_IP="https://api.wechat.qq.com/cgi-bin/getcallbackip";

    /**
     * 获取Ip地址
     */
    public static String CHECK_NET_WORK="https://api.wechat.qq.com/cgi-bin/callback/check?access_token=ACCESS_TOKEN";

    /**
     * 网页授权
     */
    public static String OAUTH2_URL_PREFIX = "https://open.wechat.qq.com/connect/oauth2/authorize";

    /**
     * 获取用户信息的token
     */
    public static  String OAUTH2_ACCESS_TOKEN="https://api.wechat.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /**
     * 获取用户信息
     */
    public static  String USER_INFO="https://api.wechat.qq.com/sns/userinfo";


    public static String TICKET="https://api.weixin.qq.com/cgi-bin/ticket/getticket";

}
