package com.message.service.template;


import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.pro.message.dto.SmsInfo;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

public class RegisterTemplate extends Template{

    private static String tpl = "您的手机验证码为：{{code}}，验证码10分钟内有效。";

    private static Mustache mustache = new DefaultMustacheFactory().compile(new StringReader(tpl), "注册");

    public RegisterTemplate(String code) {
        super(code);
    }

    @Override
    public SmsInfo parse(Map<String, String> map) {
        StringWriter sw = new StringWriter();
        mustache.execute(sw, new Object() {
            String code = map.get("code");
        });
        return new SmsInfo(code,sw.toString());
    }


}