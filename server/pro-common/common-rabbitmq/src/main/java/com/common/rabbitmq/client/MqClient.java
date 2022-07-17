package com.common.rabbitmq.client;

import com.common.rabbitmq.config.RabbitMqConfig;
import com.common.util.EnvUtil;
import com.common.util.LogUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

import static com.common.rabbitmq.config.MqSupport.initExchange;


public class MqClient {

    public static  <T extends Serializable> void send(String topic, T message) throws IOException {
        Connection connection = RabbitMqConfig.getConnection();
        Channel channel = connection.createChannel();
        try {
            String name =initExchange(topic);
            channel.exchangeDeclare(name, EnvUtil.getEnvironment().getProperty("mq.config.model"));
            byte[] bytes = SerializationUtils.serialize(message);
            channel.basicPublish(name, "", null, bytes);
            LogUtil.info("Sent '" + message.toString() + "'");
        }catch (Exception e){
            LogUtil.error("send error ",e);
        }
    }



}
