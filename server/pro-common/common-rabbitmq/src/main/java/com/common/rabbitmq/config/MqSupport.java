package com.common.rabbitmq.config;

import com.common.rabbitmq.config.model.AbstractModel;
import com.common.rabbitmq.config.model.FanoutModel;
import com.common.rabbitmq.constants.ModelEnum;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MqSupport {

    public static String initExchange(String topic){
        return topic+"-exchange";
    }

    public static String initQueue(String topic){
        return topic+"-queue";
    }


    private static final Map<ModelEnum, AbstractModel> modelMap=new HashMap<>();

    static {
        modelMap.put(ModelEnum.FANOUT,new FanoutModel());
    }

    public static AbstractModel getModel(ModelEnum modelEnum){
        return modelMap.get(modelEnum);
    }



}
