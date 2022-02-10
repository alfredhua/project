package com.common.middle.mail;

import com.common.util.LoadPropertiesUtil;
import org.apache.commons.lang3.ObjectUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class MailUtils {

    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    private static final String CONFIG_FILE="mail.config.file";

    private static final Properties properties;

    static {
        properties = LoadPropertiesUtil.loadConfig(CONFIG_FILE);
        if (!ObjectUtils.isEmpty(properties)){
            properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            properties.setProperty("mail.transport.protocol", "smtp");
            properties.setProperty("mail.smtp.socketFactory.port",properties.getProperty("mail.smtp.port"));
            properties.put("mail.smtp.auth", "true");// 发送服务器需要身份验证
        }
    }

    public static String getMailProperty(String property){
        return properties.getProperty(property);
    }

    public static void sendMail(String toMail,String title, String context)throws Exception {
        List<String> list=new ArrayList<>();
        list.add(toMail);
        sendMails(list, title,context);
    }

    public static void sendMails(List<String> toMailList, String title, String context)throws Exception {
        String emailName=properties.getProperty("mail.config.emailName");
        String password=properties.getProperty("mail.config.password");
        Session session = Session.getDefaultInstance(properties, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailName, password);
            }});
        Message msg = new MimeMessage(session);// 创建默认的 MimeMessage 对象
        msg.setFrom(new InternetAddress(emailName));//设置发件
        final int num =toMailList.size();
        InternetAddress[] addresses = new InternetAddress[num];
        for (int i = 0; i < num; i++) {
            addresses[i] = new InternetAddress( toMailList.get(i));
        }
        msg.setRecipients(Message.RecipientType.TO,addresses);//设置收件

        //设置html格式
        BodyPart html = new MimeBodyPart();
        Multipart mainPart = new MimeMultipart();
        html.setContent(context, "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        msg.setContent(mainPart);

        msg.setSentDate(new Date());
        msg.setSubject(title);
        Transport transport = session.getTransport();
        Transport.send(msg);
        transport.close();
    }

}
