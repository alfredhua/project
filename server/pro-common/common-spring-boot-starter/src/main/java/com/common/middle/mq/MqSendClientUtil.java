package com.common.middle.mq;

import com.common.util.LogUtils;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by guozhenhua
 * date 2020/7/17.
 */

@Component
@NoArgsConstructor
public class MqSendClientUtil{

    static RabbitTemplate rabbitTemplate;

    public static  void initRabbitTemplate(RabbitTemplate rabbitTemplateParam){
        rabbitTemplate=rabbitTemplateParam;
        LogUtils.info("rabbit mq init success");
    }

    public static  <T extends Serializable> void send(String topic, T message) {
        rabbitTemplate.convertAndSend(topic,message);
    }

}
