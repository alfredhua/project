package com.common.dao.entity;

import com.common.dao.enums.ConditionEnum;
import lombok.Data;

@Data
    public class Condition{

        String column;

        ConditionEnum condition;

        Object value;

        public Condition(String column, ConditionEnum condition, Object value) {
            this.column = column;
            this.condition = condition;
            this.value = value;
        }
    }