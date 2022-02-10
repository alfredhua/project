package com.common.dao.entity;

import com.common.dao.enums.ConditionEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EntityWrapper {


    List<Condition> list;

    public EntityWrapper(){
        list=new ArrayList<>();
    }

    public EntityWrapper addCondition(String column,ConditionEnum condition,Object value){
        list.add(new Condition(column,condition,value));
        return this;
    }



}
