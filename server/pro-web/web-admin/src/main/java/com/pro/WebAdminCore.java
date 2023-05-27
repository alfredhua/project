package com.pro;

import com.WebsiteCore;
import com.auth.AuthCore;
import com.common.mybatis.MybatisCore;
import com.common.rabbitmq.RabbitMqCore;
import com.common.redis.RedisCore;
import com.common.zk.ZkCore;
import com.develop.DevelopCore;
import com.message.MessageCore;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author hua
 */
@ServletComponentScan(basePackages = "com.pro.admin.filter")
@Import({ZkCore.class, MybatisCore.class, RedisCore.class,RabbitMqCore.class,
        AuthCore.class, MessageCore.class, DevelopCore.class, WebsiteCore.class})
public class WebAdminCore {

}
