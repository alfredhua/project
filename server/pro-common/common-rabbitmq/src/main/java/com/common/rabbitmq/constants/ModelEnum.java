package com.common.rabbitmq.constants;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ModelEnum {

    FANOUT("fanout");

    String model;

    ModelEnum(String model) {
        this.model = model;
    }

    public static ModelEnum getModel(String modelParam){
        return Arrays.stream( values()).filter(item -> item.getModel().equals(modelParam)).findFirst().orElse(null);
    }

}
