package com.example

import com.common.CommonCore
import org.junit.Test
import org.junit.runner.RunWith
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommonCore.class)
@MapperScan(basePackages = ["com.example.dao.mapper"])
class BaseTest {

    @Test
    void test(){
        printf "-------------11111-------"
    }
}
