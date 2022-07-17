package com.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * http 请求工具
 * @author guozhenhua
 * @date 2019/01/14
 */
public class HttpClientUtil {


    static  String defaultContentType="application/json; charset=utf-8";


    public static String get(String url, java.util.Map<String,String> parms){
        StringBuilder urlParams= new StringBuilder();
        for (Map.Entry<String, String> map:parms.entrySet()) {
            urlParams.append(map.getKey()).append("=").append(map.getValue()).append("&");
        }
        if(!StringUtils.isEmpty(urlParams.toString())){
            url=url+"?"+urlParams.substring(0,urlParams.length()-1);
        }
        HttpGet request = new HttpGet(url);
        HttpClient httpClient = HttpClients.createDefault();
        try {
            HttpResponse response = httpClient.execute(request);
            String result = null;
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
            return result;
        }catch (Exception e){
            throw new RuntimeException("Http request is error",e);
        }
    }

    public static String post(String url,  Map<String,String> paramsMap,String contentType){
        try {
            HttpClientConnectionManager hccm = createConnManager();
            CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(hccm).build();
            HttpPost requestPost = new HttpPost(url);
            if (StringUtils.isEmpty(contentType)) {
                contentType = defaultContentType;
            }
            requestPost.addHeader("Content-type", contentType);
            requestPost.setHeader("Accept", "application/json");
            requestPost.setEntity(new StringEntity(GsonUtil.toJSON(paramsMap), "UTF-8"));
            HttpResponse response = httpClient.execute(requestPost);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            return result;
        }catch (Exception e){
            throw new RuntimeException("Http post request is error",e);
        }
    }

    private static HttpClientConnectionManager createConnManager() throws KeyManagementException, NoSuchAlgorithmException {
        PoolingHttpClientConnectionManager connManager = null;
        final SSLContext slc = createIgnoreVerifySSL();
        final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(slc, NoopHostnameVerifier.INSTANCE);
        final Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sslsf).build();
        connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        return connManager;
    }

    private static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");
        X509TrustManager trustManager = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        sc.init(null, new javax.net.ssl.TrustManager[]{trustManager}, null);
        return sc;
    }



}
