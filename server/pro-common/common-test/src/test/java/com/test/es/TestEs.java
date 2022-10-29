package com.test.es;

import com.common.es.EsCore;
import com.common.es.client.EsClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EsCore.class})
public class TestEs {


    @Test
    public void save(){
        EsDemoEntity esDemoEntity = new EsDemoEntity();
        esDemoEntity.setId("1");
        EsClient.save(esDemoEntity);
    }

    @Test
    public void es(){
        EsClient.findById(EsDemoEntity.class,"1");
    }

}

