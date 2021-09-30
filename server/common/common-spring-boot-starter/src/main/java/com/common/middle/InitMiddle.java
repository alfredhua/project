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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.io.IOException;

import static com.common.middle.mq.MqServerConfig.createRabbitTemplate;
import static com.common.middle.redis.RedisConfig.createRedisTemplate;

@Slf4j
@Configuration
public class InitMiddle {

    @Autowired
    CuratorFramework curatorFramework;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    ConnectionFactory connectionFactory;

    @Autowired
    MailConfigProperties mailConfigProperties;

    @Autowired
    Environment environment;

    @Bean
    public void initAllMiddle(){
        initZk();
        initRedis();
//        initRedisLock();
        initMqSendClientUtil();
        initMailUtils();
        initEnvUtils();
    }

    /**
     * 初始化zk
     */
    private void initZk() {
        ZkUtils.initCuratorFramework(curatorFramework);
    }

    /**
     * 初始化redis
     */
    private void initRedis() {
        RedisUtils.initRedisTemplate(createRedisTemplate(redisConnectionFactory));
    }

    /**
     * 初始化redisLock
     */
    private void initRedisLock() {
        try {
            Config config = Config.fromYAML(new ClassPathResource("redisson.yaml").getInputStream());
            RedisLockUtils.initRedissonClient(Redisson.create(config));
        } catch (IOException e) {
            LogUtils.error("redisLock init error", e);
        }
    }

    /**
     * 初始化MQ
     */
    private void initMqSendClientUtil() {
        MqSendClientUtil.initRabbitTemplate(createRabbitTemplate(connectionFactory));
    }

    /**
     * 初始化邮件发送
     */
    private void initMailUtils() {
        MailUtils.initMailConfigProperties(mailConfigProperties);
    }

    /**
     * 初始化环境
     */
    private void initEnvUtils() {
        EnvUtils.initEnv(environment);
    }

}