package com.test.mybatis;

import com.common.mybatis.MybatisCore;
import com.common.util.GsonUtil;
import com.test.entity.CommonTest;
import com.test.mybatis.mapper.DemoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MybatisCore.class})
@MapperScan(basePackages = { "com.test.mybatis.mapper"})
public class TestMybatis {


    @Resource
    DemoMapper demoMapper;

    @Test
    public void insert(){
        CommonTest commonTest=new CommonTest();
        commonTest.setId(1L);
        commonTest.setName("张三");
        commonTest.setTime(LocalDateTime.now());
        demoMapper.insert(commonTest);
    }

    @Test
    public void queryById(){
        CommonTest commonTest=demoMapper.queryById(1L);
        System.out.println(GsonUtil.toJSON(commonTest));
    }

}
