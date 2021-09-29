package com.common.middle;

import com.common.middle.mail.MailConfigProperties;
import com.common.middle.mail.MailUtils;
import com.common.middle.mq.MqSendClientUtil;
import com.common.middle.redis.RedisLockUtils;
import com.common.middle.redis.RedisUtils;
import com.common.middle.zk.ZkUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static com.common.middle.mq.MqServerConfig.createRabbitTemplate;
import static com.common.middle.redis.RedisConfig.createRedisTemplate;

@Slf4j
@Configuration
public class InitMiddle {

    @PostConstruct
    @ConfigurationProperties(prefix = "spring.zk")
    public void initZk(@Autowired CuratorFramework curatorFramework) {
        ZkUtils.initCuratorFramework(curatorFramework);
    }

    @PostConstruct
    @ConditionalOnProperty(prefix = "spring.redis", value = "enable", matchIfMissing = true)
    public void initRedis(@Autowired RedisConnectionFactory connectionFactory) {
        RedisUtils.initRedisTemplate(createRedisTemplate(connectionFactory));
    }

    @PostConstruct
    @ConditionalOnProperty(value = "spring.redis.redisson.config")
    public void initRedisLock() {
        try {
            Config config = Config.fromYAML(new ClassPathResource("redisson.yaml").getInputStream());
            RedisLockUtils.initRedissonClient(Redisson.create(config));
        } catch (IOException e) {
            log.error("redisLock init error", e);
        }
    }

    @PostConstruct
    @ConditionalOnProperty(prefix = "spring.rabbitmq", value = "enable", matchIfMissing = true)
    public void initMqSendClientUtil(@Autowired ConnectionFactory connectionFactory) {
        MqSendClientUtil.initRabbitTemplate(createRabbitTemplate(connectionFactory));
    }

    @PostConstruct
    @ConfigurationProperties(prefix = "mail")
    public void initMailUtils(@Autowired MailConfigProperties mailConfigProperties) {
        MailUtils.initMailConfigProperties(mailConfigProperties);
    }

}