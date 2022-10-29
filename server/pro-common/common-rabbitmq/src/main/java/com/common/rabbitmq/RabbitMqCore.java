package com.common.rabbitmq;

import com.common.rabbitmq.client.MqClient;
import com.common.rabbitmq.config.RabbitInitServer;
import com.common.rabbitmq.config.RabbitMqProperties;
import com.common.rabbitmq.consumer.AbstractMqConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;
import java.util.List;

@EnableAutoConfiguration
@ComponentScan
public class RabbitMqCore {

    @Autowired(required = false)
    List<AbstractMqConsumer> list;

    @Resource
    RabbitMqProperties rabbitMqProperties;

    @Bean(initMethod = "init")
    public RabbitInitServer rabbitMq(){
        MqClient.setRabbitMqProperties(rabbitMqProperties);
        return new RabbitInitServer(list,rabbitMqProperties);
    }

}
