package com.message.util;

import com.common.middle.redis.RedisUtils;
import com.common.util.GsonUtils;
import com.common.util.HttpClient;
import com.google.gson.reflect.TypeToken;
import com.message.constants.WeChatConstantEnum;
import com.message.dto.WeChatAccessToken;
import com.message.dto.WeChatJsapiTicket;
import com.message.dto.WeChatOauth2AccessToken;
import com.message.dto.WeChatShareInfo;
import com.message.dto.entity.WeChatUserInfo;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by guozhenhua
 * date 2020/8/10.
 * 微信公众号下接口
 */
public class WeChatPublicUtil {

    private String public_app_id;

    private String public_app_secret;

    public WeChatPublicUtil(String public_app_id, String public_app_secret) {
        this.public_app_id = public_app_id;
        this.public_app_secret = public_app_secret;
    }

    private String accessTokenKey(){
        return "wechat:ac:"+ public_app_id;
    }

    private String jsapiTicketKey(){
        return "wechat:jsapi:"+public_app_id;
    }

    /**
     * H5 微信授权链接
     */
    public String generateOAuth2Url(String scope, String redirectUrl, String state) {
        if (ObjectUtils.isEmpty(scope)){
            scope="snsapi_userinfo";
        }
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(WeChatUrl.OAUTH2_URL_PREFIX)
                .append("?appid=").append(public_app_id)
                .append("&redirect_uri=").append(redirectUrl)
                .append("&response_type=code&scope=").append(scope)
                .append("&state=").append(state).append("#wechat_redirect");
        return  stringBuilder.toString();
    }

    /**
     * 获取微信分享的accessToken
     */
    public WeChatAccessToken getAccessToken() throws Exception {
        WeChatAccessToken weChatAccessToken = RedisUtils.objectGet(accessTokenKey());
        if (!ObjectUtils.isEmpty(weChatAccessToken)){
            return weChatAccessToken;
        }
        Map<String,String> map = new HashMap<>();
        map.put("grant_type", "client_credential");
        map.put("appid", public_app_id);
        map.put("secret", public_app_secret);
        String s = HttpClient.get(WeChatUrl.ACCESS_TOKEN, map);
        WeChatAccessToken newWeChatAccessToken =GsonUtils.gson.fromJson(s,new TypeToken<WeChatAccessToken>(){}.getType());
        RedisUtils.objectSet(accessTokenKey(), WeChatConstantEnum.ACCESS_TOKEN.getTimeOut(), newWeChatAccessToken);
        return newWeChatAccessToken;
    }

    /**
     * 获取微信分享信息
     */
    public WeChatShareInfo getWXShareInfo(String url) throws Exception {
        WeChatJsapiTicket ticket=getJsapiTicket();
        if (ObjectUtils.isEmpty(ticket)){

        }
        String noncestr = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        return sign(ticket.getTicket(), url, timestamp, noncestr).addAppId(public_app_id);
    }

    /**
     * 获取Ip地址接口
     */
    public List<String> getWeiXinIp(String accessToken)throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("access_token", accessToken);
        String s = HttpClient.get(WeChatUrl.WEI_IP, map);
        return  GsonUtils.gson.fromJson(s,new TypeToken<List<String>>(){}.getType());
    }

    /**
     * 微信网络检测接口
     */
    public String checkNetWork(String accessToken)throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("action", "all");
        map.put("check_operator", "DEFAULT");
        return HttpClient.post(WeChatUrl.CHECK_NET_WORK.replace("ACCESS_TOKEN",accessToken),map,null);
    }


    /**
     * 微信授权获取用户信息
     */
    public WeChatUserInfo getUserInfoByOauth2Code(String code){
        try {
            WeChatOauth2AccessToken accessToken = getOauth2AccessToken(code);
            Map<String,String> map=new HashMap();
            map.put("access_token",accessToken.getAccess_token());
            map.put("openid", accessToken.getOpenid());
            map.put("lang","zh_CN");
            String result = HttpClient.get(WeChatUrl.USER_INFO, map);
            if(!StringUtils.isEmpty(result)){
                return GsonUtils.gson.fromJson(result,new TypeToken<WeChatUserInfo>(){}.getType());
            }
            throw new RuntimeException("get com.auth.user-info-by-oauth2-access-token result:"+result);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private WeChatJsapiTicket getJsapiTicket()throws Exception {
        WeChatJsapiTicket jsapiTicket = RedisUtils.objectGet(jsapiTicketKey());
        if (!ObjectUtils.isEmpty(jsapiTicket)){
            return jsapiTicket;
        }
        WeChatAccessToken access = getAccessToken();
        Map<String,String> map=new HashMap();
        map.put("access_token", access.getAccess_token());
        map.put("type", "jsapi");
        try {
            String result = HttpClient.get(WeChatUrl.TICKET, map);
            if(!StringUtils.isEmpty(result)){
                jsapiTicket = GsonUtils.gson.fromJson(result, WeChatJsapiTicket.class);
                jsapiTicket.setExpires_at(LocalDateTime.now().plusSeconds(jsapiTicket.getExpires_in()-60-60)); //再减60s 使得更新程序在redis失效前进行操作
                RedisUtils.objectSet(jsapiTicketKey(), (long)jsapiTicket.getExpires_in()-60,jsapiTicket);
            }
            return jsapiTicket;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static WeChatShareInfo sign(String jsapi_ticket, String url, String timestamp, String noncestr)throws Exception {
        //注意这里参数名必须全部小写，且必须有序
        String parms = "jsapi_ticket=" + jsapi_ticket +"&noncestr=" + noncestr +"&timestamp=" + timestamp+"&url=" + url;
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(parms.getBytes("UTF-8"));
        String signature = Hex.encodeHexString(crypt.digest());
        WeChatShareInfo wxShareInfo=new WeChatShareInfo();
        wxShareInfo.setUrl(url);
        wxShareInfo.setNoncestr(noncestr);
        wxShareInfo.setTimestamp(timestamp);
        wxShareInfo.setSignature(signature);
        return wxShareInfo;
    }

    private WeChatOauth2AccessToken getOauth2AccessToken(String code){
        Map<String,String> map=new HashMap();
        map.put("grant_type","authorization_code");
        map.put("appid", public_app_id);
        map.put("secret",public_app_secret);
        map.put("code",code);
        try {
            String result = HttpClient.get(WeChatUrl.OAUTH2_ACCESS_TOKEN.replace("APPID",public_app_id).replace("CODE", code), map);
            if(!StringUtils.isEmpty(result)){
                return  GsonUtils.gson.fromJson(result,new TypeToken<WeChatOauth2AccessToken>(){}.getType());
            }
            throw new RuntimeException("get-oauth2-access-token result:"+result);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }





}
