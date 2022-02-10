package com.common.dao.entity;

import lombok.Data;

import java.util.Map;

@Data
public class TableInfo {

    /**
     * 表名
     */
    String tableName;

    /**
     * 字段信息
     * key: 字段名
     * FieldInfo: 字段信息(列名，字段类型)
     */
    Map<String,FieldInfo> fieldMap;


    public FieldInfo getByColumnName(String columnName){
        return fieldMap.values().stream().filter(fieldInfo -> columnName.equals(fieldInfo.getColumnName())).findFirst().orElse(null);
    }

}
