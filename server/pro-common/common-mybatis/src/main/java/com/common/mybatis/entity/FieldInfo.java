package com.common.mybatis.entity;

import lombok.Data;

@Data
public class FieldInfo {
    /**
     * 数据库列名
     */
    String columnName;

    /**
     * 字段名
     */
    String filedName;

    /**
     * 字段类型
     */
    Class<?> clazz;
}