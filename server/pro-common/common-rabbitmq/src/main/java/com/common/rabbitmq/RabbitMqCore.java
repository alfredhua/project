package com.common.rabbitmq;

import com.common.CommonCore;
import com.common.rabbitmq.config.RabbitMqConfig;
import com.common.rabbitmq.consumer.AbstractMqConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

@Configuration
@Import(CommonCore.class)
public class RabbitMqCore {

    @Autowired(required = false)
    List<AbstractMqConsumer> list;

    @Bean(initMethod = "init")
    public RabbitMqConfig rabbitMq(){
        return new RabbitMqConfig(list);
    }




}
