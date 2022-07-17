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

public class RabbitMqConfig {

    private static ConnectionFactory factory=null;

    List<AbstractMqConsumer> list;

    public RabbitMqConfig(List<AbstractMqConsumer> list) {
       this.list = list;
    }

    public void init(){
        Environment environment = EnvUtil.getEnvironment();
        factory=new ConnectionFactory();
        factory.setUsername(environment.getProperty("mq.config.userName"));
        factory.setPassword(environment.getProperty("mq.config.password"));
        factory.setVirtualHost(environment.getProperty("mq.config.virtualHost"));
        factory.setHost(environment.getProperty("mq.config.host"));
        factory.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("mq.config.port"))));
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
                Environment environment = EnvUtil.getEnvironment();
                String modelStr = environment.getProperty("mq.config.model");
                if (ObjectUtils.isEmpty(modelStr)){
                    throw new RuntimeException("mq.config.model is empty");
                }
                ModelEnum model = ModelEnum.getModel(modelStr);
                if (!Optional.ofNullable(model).isPresent()){
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
