package com.pro;

import com.common.redis.RedisCore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author hua
 */
@Import({RedisCore.class})
public class RedisTestCore {

}
