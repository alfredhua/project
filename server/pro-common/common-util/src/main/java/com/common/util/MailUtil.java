package com.common.util;

import com.common.entity.MailEntity;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 邮件发送
 * @author hua
 */
public class MailUtil {

    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    private static MailEntity mailEntity;

    private static final Properties properties = new Properties();

    public static void initMail(MailEntity entity){
        mailEntity = entity;
        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.socketFactory.port",entity.getPort());
        properties.put("mail.smtp.auth", "true");
    }

    public static void sendMail(String toMail,String title, String context)throws Exception {
        List<String> list=new ArrayList<>();
        list.add(toMail);
        sendMails(list, title,context);
    }

    public static void sendMails(List<String> toMailList, String title, String context)throws Exception {
        Session session = Session.getDefaultInstance(properties, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailEntity.getEmailName(), mailEntity.getPassword());
            }});
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(mailEntity.getEmailName()));
        final int num =toMailList.size();
        InternetAddress[] addresses = new InternetAddress[num];
        for (int i = 0; i < num; i++) {
            addresses[i] = new InternetAddress( toMailList.get(i));
        }
        msg.setRecipients(Message.RecipientType.TO,addresses);

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
