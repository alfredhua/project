package com.common.dao.util;

import com.common.dao.entity.Condition;
import com.common.dao.enums.ConditionEnum;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;
import java.util.Map;

public class EntityWrapperUtils {


    private static final Map<ConditionEnum,String> map=new HashMap<>();

    static {
        map.put(ConditionEnum.eq,"=");
        map.put(ConditionEnum.le,"<=");
        map.put(ConditionEnum.lt,"<");
        map.put(ConditionEnum.gt,">");
        map.put(ConditionEnum.ge,">=");
    }

    public static SQL getWhereSql(Condition condition,SQL sql){
        if (map.containsKey(condition.getCondition())){
            return sql.WHERE(condition.getColumn()+" "+map.get(condition.getCondition())+" ? ");
        }
        if (condition.getCondition().equals(ConditionEnum.like)){
            return sql.WHERE(condition.getColumn()+" like CONCAT('%',?,'%') ");
        }
        return null;
    }

}
