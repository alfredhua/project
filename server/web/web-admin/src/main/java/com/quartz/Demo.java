package com.quartz;

import com.common.util.LogUtils;
import org.springframework.stereotype.Component;

/**
 * @auth guozhenhua
 * @date 2018/12/17
 */
@Component
public class Demo {


    private final static String TIME =  "0/1 * * * * ?";

//    @Scheduled(cron=TIME)
    public void cronJob(){
        LogUtils.info(" >>cron执行....");
    }
}