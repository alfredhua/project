package com.test;

import com.common.CommonCore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guozhenhua
 * @date 2020/08/23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommonCore.class)
public class CommonTest {

    @Autowired
    MailUtil mailUtils;

    @Test
    public void test() throws Exception {
        mailUtils.sendMail("751724893qq.com","标题","内容");

    }

}
