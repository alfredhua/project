package com.pro.job;

import com.common.util.LogUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @auth guozhenhua
 * @date 2018/12/17
 */
@Component
public class Demo {

    @Scheduled(cron="0/1 * * * * ?")
    public void cronJob(){
        LogUtil.info(" >>cron执行....");
    }
}