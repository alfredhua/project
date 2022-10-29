package com.test.mq;

import com.common.rabbitmq.consumer.AbstractMqConsumer;
import org.springframework.stereotype.Component;

@Component
public class MqDemoConsumer extends AbstractMqConsumer {

    @Override
    public String getTopic() {
        return "DEMO";
    }

    @Override
    public String consume(Object o) {
        System.out.println(o);
        return null;
    }
}
