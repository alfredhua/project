package com.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @auth guozhenhua
 * @date 2018/12/17
 */
@Component
public class Demo {

    private static Logger logger = LoggerFactory.getLogger(Demo.class);

    private final static String TIME =  "0/1 * * * * ?";

//    @Scheduled(cron=TIME)
    public void cronJob(){
        logger.info(" >>cron执行....");
    }
}