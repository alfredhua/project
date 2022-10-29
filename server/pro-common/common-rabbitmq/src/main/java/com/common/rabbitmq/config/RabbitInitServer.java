package com.common.rabbitmq.config;

import com.common.rabbitmq.constants.ModelEnum;
import com.common.rabbitmq.consumer.AbstractMqConsumer;
import com.common.util.EnvUtil;
import com.common.util.LogUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.env.Environment;
import org.springframework.util.SerializationUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RabbitInitServer {

    private static ConnectionFactory factory=null;

    List<AbstractMqConsumer> list;


    RabbitMqProperties rabbitMqProperties;

    public RabbitInitServer(List<AbstractMqConsumer> list,RabbitMqProperties rabbitMqProperties) {
        this.list = list;
        this.rabbitMqProperties=rabbitMqProperties;
    }

    public void init(){
        factory=new ConnectionFactory();
        factory.setUsername(rabbitMqProperties.getUserName());
        factory.setPassword(rabbitMqProperties.getPassword());
        factory.setVirtualHost(rabbitMqProperties.getVirtualHost());
        factory.setHost(rabbitMqProperties.getHost());
        factory.setPort(Integer.parseInt(Objects.requireNonNull(rabbitMqProperties.getPort())));
        LogUtil.info("rabbitmq init success");
        this.start();
    }

    private void start(){
        try{
            if (list==null || list.isEmpty()){
                return;
            }
            Connection connection = getConnection();
            Channel channel = connection.createChannel();
            for (AbstractMqConsumer abstractMqConsumer:list){
                String model = rabbitMqProperties.getModel();
                if (ObjectUtils.isEmpty(model)){
                    throw new RuntimeException("mq.config.model is empty");
                }
                ModelEnum modelEnum = ModelEnum.getModel(model);
                if (!Optional.ofNullable(modelEnum).isPresent()){
                    throw new RuntimeException("model config is error");
                }
                String queueName = MqSupport.getModel(ModelEnum.FANOUT).setModelAndReturnQueue(channel, abstractMqConsumer);
                channel.basicConsume(queueName, abstractMqConsumer.autoAck(), (consumerTag, delivery) -> {
                    //处理接收MQ
                    Object message = SerializationUtils.deserialize(delivery.getBody());
                    System.out.println("Received :" + message);
//                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), true);
                    abstractMqConsumer.consume(message);
                }, consumerTag -> { });
            }
        }catch (Exception e){
            LogUtil.error("mq start error",e);
        }
    }



    public static Connection getConnection(){
        try {
            if (ObjectUtils.isEmpty(factory)){
                throw new RuntimeException("factory is empty");
            }
            return factory.newConnection();
        } catch (Exception e) {
            LogUtil.error("mq connection error",e);
        }
        return null;
    }







}
