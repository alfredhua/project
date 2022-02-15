package com.common.middle.mq;

import com.common.util.LoadPropertiesUtil;
import com.common.util.LogUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.Properties;

public class MqConfig {


    private static final String CONFIG_FILE="mq.config.file";

    private static ConnectionFactory factory;

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

    public static void start(List<AbstractMqConsumer> list){
        try{
            if (list==null||list.isEmpty()){
                LogUtil.info(" mq topic is empty");
                return;
            }
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            for (AbstractMqConsumer abstractMqConsumer:list){
                String topic = abstractMqConsumer.getTopic();

                String exchangeName = MqSupple.initExchange(topic);
                channel.exchangeDeclare(exchangeName, MqSupple.TYPE);

                String queueName = MqSupple.initQueue(topic);
                channel.queueDeclare(queueName, true, false, false, null);

                channel.queueBind(queueName, exchangeName, topic);

                channel.basicConsume(queueName, abstractMqConsumer.autoAck(), (consumerTag, delivery) -> {
                    //处理接收MQ
                    String message = new String(delivery.getBody(), "UTF-8");
                    abstractMqConsumer.consume(message);
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                }, consumerTag -> { });
            }
        }catch (Exception e){
            LogUtil.error("mq start error",e);
        }
    }



}
