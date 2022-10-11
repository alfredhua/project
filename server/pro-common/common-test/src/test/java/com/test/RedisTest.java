package com.test;

import com.common.redis.client.RedisClient;
import com.pro.RedisTestCore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author guozhenhua
 * @date 2020/08/23
 */
@SpringBootTest(properties = "redis.properties",classes = RedisTestCore.class)
public class RedisTest {

    @Test
    public void objectSet(){
        RedisClient.objectSet("aaa",1000L,"aaaa");
    }

    @Test
    public void update(){
    }


}
