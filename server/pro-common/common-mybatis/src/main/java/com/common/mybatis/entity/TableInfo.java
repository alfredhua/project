package com.common.mybatis.entity;

import lombok.Data;

import java.util.List;

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
    List<FieldInfo> fieldInfoList;


    public FieldInfo getByColumnName(String columnName){
        return fieldInfoList.stream().filter(fieldInfo -> columnName.equals(fieldInfo.getColumnName())).findFirst().orElse(null);
    }




}
