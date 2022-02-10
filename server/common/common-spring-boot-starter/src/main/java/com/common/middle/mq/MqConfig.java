package com.common.middle.mq;

import com.common.util.LoadPropertiesUtil;
import com.common.util.LogUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MqConfig {


    private static final String CONFIG_FILE="mq.config.file";

    private static ConnectionFactory factory;

    static List<AbstractMqConsumer> list;

    static {
        factory = new ConnectionFactory();
        Properties properties = LoadPropertiesUtil.loadConfig(CONFIG_FILE);
        if (!ObjectUtils.isEmpty(properties)) {
            factory.setUsername(properties.getProperty("mq.userName"));
            factory.setPassword(properties.getProperty("mq.password"));
            factory.setVirtualHost(properties.getProperty("mq.virtualHost"));
            factory.setHost(properties.getProperty("mq.host"));
            factory.setPort(Integer.parseInt(properties.getProperty("mq.port")));
        }
    }

    public static Connection getConnection(){
        try {
            return factory.newConnection();
        } catch (Exception e) {
            LogUtil.error("mq connection error",e);
        }
        return null;
    }

    public static void start(){
        try{
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            for (AbstractMqConsumer abstractMqConsumer:list){
                String topic = abstractMqConsumer.getTopic();
                String exchangeName = MqSupple.initExchange(topic);
                channel.exchangeDeclare(exchangeName, MqSupple.TYPE);
                String queueName = MqSupple.initQueue(topic);
                channel.queueBind(queueName, exchangeName, "");
                channel.basicConsume(queueName, abstractMqConsumer.autoAck(), (consumerTag, delivery) -> {
                    //处理接收MQ
                    String message = new String(delivery.getBody(), "UTF-8");
                    System.out.println(topic+" Received :" + message);
                    abstractMqConsumer.consume(message);
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                }, consumerTag -> { });
            }
        }catch (Exception e){
            LogUtil.error("mq start error",e);
        }
    }



}
