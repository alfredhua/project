package com.common.middle.mq;

import com.common.util.LogUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

import static com.common.middle.mq.MqSupple.initExchange;

public class MqClientUtil {

    public static  <T extends Serializable> void send(String topic, T message) throws IOException, TimeoutException {
        Connection connection = MqConfig.getConnection();
        Channel channel = connection.createChannel();
        try {
            String name =initExchange(topic);
            channel.exchangeDeclare(name, MqSupple.TYPE);
            byte[] bytes = SerializationUtils.serialize(message);
            channel.basicPublish(name, topic, null, bytes);
            LogUtil.info("Sent '" + message.toString() + "'");
        }catch (Exception e){
            LogUtil.error("send error ",e);
        }finally {
            channel.close();
            connection.close();
        }
    }



}
