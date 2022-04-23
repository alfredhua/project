package com.develop.constants;

import lombok.Getter;

/**
 * @auth guozhenhua
 * @date 2021/01/22
 */
@Getter
public enum NodePathEnum {


    DEMO("demo","demo","demo"),
    ;

    String name;
    String default_value;
    String desc;
    private static final String PRE="/";


    public String getNodePath(){
        return PRE+this.name;
    }

    NodePathEnum(String name, String default_value, String desc) {
        this.name = name;
        this.default_value = default_value;
        this.desc = desc;
    }

    public static NodePathEnum getNodePathByName(String name){
        NodePathEnum[] values = values();
        for (NodePathEnum nodePath:values) {
            if (nodePath.getName().equals(name)){
                return nodePath;
            }
        }
        return null;
    }
}
