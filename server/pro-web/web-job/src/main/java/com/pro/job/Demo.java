package com.pro.job;

import com.common.util.LogUtil;

/**
 * @auth guozhenhua
 * @date 2018/12/17
 */
//@Component
public class Demo {


    private final static String TIME =  "0/1 * * * * ?";

//    @Scheduled(cron=TIME)
    public void cronJob(){
        LogUtil.info(" >>cron执行....");
    }
}