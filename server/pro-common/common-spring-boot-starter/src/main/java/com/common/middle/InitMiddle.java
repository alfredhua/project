package com.common.middle;

import com.common.middle.redis.RedisLockUtil;
import com.common.middle.redis.RedisUtil;
import com.common.util.EnvUtil;
import com.common.util.LogUtil;
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
    Environment environment;

    @Bean
    public void initAllMiddle(){
        initRedis();
        initEnvUtils();
    }

    /**
     * 初始化redis
     */
    private void initRedis() {
        RedisUtil.initRedisTemplate(createRedisTemplate(redisConnectionFactory));
    }

    /**
     * 初始化redisLock
     */
    private void initRedisLock() {
        try {
            Config config = Config.fromYAML(new ClassPathResource("redisson.yaml").getInputStream());
            RedisLockUtil.initRedissonClient(Redisson.create(config));
        } catch (IOException e) {
            LogUtil.error("redisLock init error", e);
        }
    }


    /**
     * 初始化环境
     */
    private void initEnvUtils() {
        EnvUtil.initEnv(environment);
    }

}