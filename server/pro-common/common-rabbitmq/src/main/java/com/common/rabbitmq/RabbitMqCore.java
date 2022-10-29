package com.common.rabbitmq;

import com.common.rabbitmq.config.RabbitMqConfig;
import com.common.rabbitmq.consumer.AbstractMqConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@EnableAutoConfiguration
@ComponentScan
public class RabbitMqCore {

    @Autowired(required = false)
    List<AbstractMqConsumer> list;

    @Bean(initMethod = "init")
    public RabbitMqConfig rabbitMq(){
        return new RabbitMqConfig(list);
    }

}
