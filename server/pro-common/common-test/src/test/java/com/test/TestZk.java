package com.test;

import com.common.zk.ZkCore;
import com.common.zk.client.ZkClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ZkCore.class})
public class TestZk {

    @Test
    public void zk(){
        ZkClient.create("/ccc","aaaa");
    }
}
