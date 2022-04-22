package com.common.mybatis.entity;

import com.common.mybatis.enums.ConditionEnum;
import lombok.Data;

@Data
public class Condition {

    String column;

    ConditionEnum condition;

    Object value;

    public Condition(String column, ConditionEnum condition, Object value) {
        this.column = column;
        this.condition = condition;
        this.value = value;
    }

}
 