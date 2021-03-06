package com.admin.filter;

import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.response.ErrorResponse;
import com.common.util.EnvUtils;
import com.common.util.GsonUtils;
import com.common.util.SignUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @auth guozhenhua
 * @date 2019/11/07
 */

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "sign")
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*",filterName = "SignFilter")
public class SignFilter implements Filter {

    protected static final Logger logger = LoggerFactory.getLogger(SignFilter.class);

    /**
     *η­Ύε
     */
    private String app_key;
    /**
     * εε
     */
    private String domain;

    @Autowired
    EnvUtils envUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(req);
        String body = getBodyString(requestWrapper);
        //εΌεη―ε’οΌη΄ζ₯ζΎθΏ
        if (envUtils.isDevActive()){
            continueFilter(resp,chain,requestWrapper);
            return;
        }

        if(StringUtils.isEmpty(body)){
            errorResponse(new ErrorResponse(SysErrorCodeEnum.SIGN_NULL.getCode(),SysErrorCodeEnum.SIGN_NULL.getMsg()),resp);
            return;
        }
        //θΏεηΌΊε°sign
        Map maps =GsonUtils.jsonStringToMap(body);
        if (!maps.containsKey("sign")){
            errorResponse(new ErrorResponse(SysErrorCodeEnum.SIGN_NULL.getCode(),SysErrorCodeEnum.SIGN_NULL.getMsg()),resp);
            return;
        }
        //θΏθ‘ζεΊ
        TreeMap treeMap=orderParams(maps);
        //ε―Ήη°ε¨ζ°ζ?ε η­Ύ
        String sign = getSign(req.getRequestURI(),req.getMethod(),treeMap);
        //signη­Ύειθ――
        logger.info("sign:"+sign);
        if (!sign.equals(treeMap.get("sign"))){
            errorResponse(new ErrorResponse(SysErrorCodeEnum.SIGN_ERROR.getCode(),SysErrorCodeEnum.SIGN_ERROR.getMsg()),resp);
            return;
        }
        continueFilter(resp,chain,requestWrapper);
    }

    /**
     * εζ°ζεΊ
     * @param maps
     * @return
     */
    private TreeMap orderParams(Map maps){
        TreeMap<String,String> treeMap= new TreeMap<>();
        Set set = maps.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            String next = (String)iterator.next();
            if(!ObjectUtils.isEmpty(maps.get(next))){
                treeMap.put(next,maps.get(next).toString());
            }
        }
        treeMap.put("app_key",this.getApp_key());
        return treeMap;
    }

    /**
     * ε η­Ύ
     * @param requestUri
     * @param method
     * @param treeMap
     * @return
     */
    private String getSign(String requestUri,String method , TreeMap treeMap){
        StringBuilder Stringbuilder=new StringBuilder();
        Stringbuilder.append(method).append(this.getDomain()).append(requestUri).append("?");
        Set setKeys = treeMap.keySet();
        Iterator iterator1 = setKeys.iterator();
        //θΏθ‘signζ°ζ?η»θ£
        while(iterator1.hasNext()){
            String next = (String)iterator1.next();
            if (!next.equals("sign")) {
                Stringbuilder.append(next).append("=").append(treeMap.get(next)).append("&");
            }
        }
        String substring = Stringbuilder.substring(0, Stringbuilder.length() - 1);
        logger.info("η­Ύεζ°ζ?:"+substring);
        return SignUtil.sign(substring);
    }

    /**
     * ιθ――θΏε
     * @param errorResponse
     * @param resp
     */
    private void errorResponse(ErrorResponse errorResponse,HttpServletResponse resp){
        try {
            byte[] responseBytes = new ObjectMapper().writeValueAsString(errorResponse).getBytes();
            resp.setContentType("text/html;charset=UTF-8");
            resp.setHeader("Content-Type", "application/json");
            resp.setStatus(200);
            resp.getOutputStream().write(responseBytes);
        }catch (Exception e){
            throw new RuntimeException("error",e);
        }
    }

    /**
     * η»§η»­ζ§θ‘filter
     * @param resp
     * @param chain
     * @param requestWrapper
     * @throws IOException
     * @throws ServletException
     */
    private void continueFilter(HttpServletResponse resp,FilterChain chain,ServletRequest requestWrapper)throws IOException, ServletException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Cache-Control, token");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("Content-Type", "application/json");
        chain.doFilter(requestWrapper, resp);
    }

    class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
        private final byte[] body;
        public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            body = getBodyString(request).getBytes(Charset.forName("UTF-8"));
        }
        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }
        @Override
        public ServletInputStream getInputStream() throws IOException {
            final ByteArrayInputStream bais = new ByteArrayInputStream(body);
            return new ServletInputStream() {
                @Override
                public int read() throws IOException {
                    return bais.read();
                }
                @Override
                public boolean isFinished() {
                    return false;
                }
                @Override
                public boolean isReady() {
                    return false;
                }
                @Override
                public void setReadListener(ReadListener readListener) {
                }
            };
        }
    }

    private static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
