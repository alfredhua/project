package com.pro;

import com.common.redis.RedisCore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author hua
 */
@SpringBootApplication
@Import({RedisCore.class})
public class RedisTestCore {

}
