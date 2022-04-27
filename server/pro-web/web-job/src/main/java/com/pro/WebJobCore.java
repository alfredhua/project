package com.pro;

import com.WebsiteCore;
import com.auth.AuthCore;
import com.common.mybatis.MybatisCore;
import com.common.rabbitmq.RabbitMqCore;
import com.common.redis.RedisCore;
import com.common.zk.ZkCore;
import com.develop.DevelopCore;
import com.message.MessageCore;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hua
 */
@EnableScheduling
@Import({ZkCore.class, MybatisCore.class, RedisCore.class,ZkCore.class, RabbitMqCore.class,
        AuthCore.class, MessageCore.class, DevelopCore.class, WebsiteCore.class})
public class WebJobCore {


}
