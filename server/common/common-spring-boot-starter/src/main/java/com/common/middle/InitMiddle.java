package com.common.middle;

import com.common.middle.mail.MailConfigProperties;
import com.common.middle.mail.MailUtils;
import com.common.middle.mq.MqSendClientUtil;
import com.common.middle.redis.RedisLockUtils;
import com.common.middle.redis.RedisUtils;
import com.common.middle.zk.ZkUtils;
import com.common.util.EnvUtils;
import com.common.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static com.common.middle.mq.MqServerConfig.createRabbitTemplate;
import static com.common.middle.redis.RedisConfig.createRedisTemplate;

@Slf4j
@Configuration
public class InitMiddle {

    @Autowired CuratorFramework curatorFramework;
    @PostConstruct
    public void initZk() {
        ZkUtils.initCuratorFramework(curatorFramework);
        LogUtils.info("zk init success");
    }

    @Autowired RedisConnectionFactory redisConnectionFactory;
    @PostConstruct
    public void initRedis() {
        RedisUtils.initRedisTemplate(createRedisTemplate(redisConnectionFactory));
        LogUtils.info("redis init success");
    }

    @PostConstruct
    public void initRedisLock() {
        try {
            Config config = Config.fromYAML(new ClassPathResource("redisson.yaml").getInputStream());
            RedisLockUtils.initRedissonClient(Redisson.create(config));
            LogUtils.info("redis lock init success");
        } catch (IOException e) {
            LogUtils.error("redisLock init error", e);
        }
    }

    @Autowired ConnectionFactory connectionFactory;
    @PostConstruct
    public void initMqSendClientUtil() {
        MqSendClientUtil.initRabbitTemplate(createRabbitTemplate(connectionFactory));
        LogUtils.info("MQ init success");
    }

    @Autowired MailConfigProperties mailConfigProperties;
    @PostConstruct
    public void initMailUtils() {
        MailUtils.initMailConfigProperties(mailConfigProperties);
        LogUtils.info("mail init success");
    }

    @Autowired Environment environment;
    @PostConstruct
    public void initEnvUtils() {
        EnvUtils.initEnv(environment);
        LogUtils.info("environment init success");
    }

}