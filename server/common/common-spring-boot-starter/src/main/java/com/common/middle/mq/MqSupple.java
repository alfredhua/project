package com.common.middle.mq;

public class MqSupple {

    public static final String TYPE="fanout";

    public static String initExchange(String topic){
        return topic+"-exchange";
    }


    public static String initQueue(String topic){
        return topic+"-queue";
    }

}
