package com.common.rabbitmq.config.model;

import com.common.rabbitmq.config.MqSupport;
import com.common.rabbitmq.constants.ModelEnum;
import com.common.rabbitmq.consumer.AbstractMqConsumer;
import com.rabbitmq.client.Channel;

public class FanoutModel extends AbstractModel {

    @Override
    public String setModelAndReturnQueue(Channel channel, AbstractMqConsumer abstractMqConsumer)throws Exception {
        String topic = abstractMqConsumer.getTopic();
        String exchangeName = MqSupport.initExchange(topic);
        channel.exchangeDeclare(exchangeName, ModelEnum.FANOUT.getModel());
        String queueName = MqSupport.initQueue(topic);
        channel.queueDeclare(queueName,true,true,true,null);
        channel.queueBind(queueName, exchangeName, "");
        return queueName;
    }
}
