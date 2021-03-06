package com.common.mq;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
import org.springframework.stereotype.Component;

/**
 * @author guozhenhua
 * @date 2019/01/22
 */
@Getter
@Setter
@Configuration
@Component
@ConditionalOnClass(MqSendClientUtil.class)
@ConditionalOnProperty(prefix = "spring.rabbitmq", value = "enable", matchIfMissing = true)
public class MqServerConfig {

    private static final Logger logger = LoggerFactory.getLogger(MqServerConfig.class);

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        // ???????????????????????????Exchange
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause )->{
//            if (ack) {
//                logger.info("?????????????????????Exchange",correlationData, ack, cause);
//            } else {
//                logger.info("???????????????Exchange??????, {}, cause: {}", correlationData, cause);
//            }
//        });
//        rabbitTemplate.setMandatory(true);// ??????setReturnCallback??????????????????mandatory=true, ??????Exchange????????????Queue?????????????????????, ?????????????????????
//        // ???????????????Exchange?????????Queue, ??????: ????????????????????????, ???????????????Exchange?????????Queue??????????????????????????????
//        rabbitTemplate.setReturnCallback(( message, replyCode, replyText, exchange, routingKey )->{
//            logger.info("?????????Exchange?????????Queue??????: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}", exchange, routingKey, replyCode, replyText, message);
//        });
        return rabbitTemplate;
    }

}
