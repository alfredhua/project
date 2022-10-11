package com.test;

import com.common.CommonCore;
import com.common.redis.client.RedisClient;
import com.pro.RedisTestCore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author guozhenhua
 * @date 2020/08/23
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonCore.class,RedisTestCore.class})
public class RedisTest {

    @Test
    public void objectSet(){
        RedisClient.objectSet("aaa",1000L,"aaaa");
    }

    @Test
    public void update(){
    }


}
