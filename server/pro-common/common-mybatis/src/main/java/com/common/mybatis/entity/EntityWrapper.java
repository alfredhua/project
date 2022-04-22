package com.common.mybatis.entity;

import com.common.mybatis.enums.ConditionEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EntityWrapper {


    List<Condition> list;

    public EntityWrapper(){
        list=new ArrayList<>();
    }

    public EntityWrapper addCondition(String column, ConditionEnum condition,Object value){
        list.add(new Condition(column,condition,value));
        return this;
    }


}