package com.test.mybatis;

import com.common.redis.RedisCore;
import com.test.mybatis.mapper.DemoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisCore.class})
public class TestMybatis {


    @Resource
    DemoMapper demoMapper;

    @Test
    public void findId(){
        demoMapper.queryById(1L);
    }

}
