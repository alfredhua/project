package com.test.mq;

import com.common.rabbitmq.RabbitMqCore;
import com.common.rabbitmq.client.MqClient;
import com.test.entity.CommonTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author guozhenhua
 * @date 2020/08/23
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RabbitMqCore.class})
public class TestRabbitMq {


    @Test
    public void sendMessage(){
        try {
            CommonTest demo = new CommonTest();
            demo.setId(1L);
            demo.setName("张三");
            demo.setTime(LocalDateTime.now());
            MqClient.send("DEMO",demo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
