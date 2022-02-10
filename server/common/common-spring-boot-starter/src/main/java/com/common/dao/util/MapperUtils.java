package com.common.dao.util;

import com.common.dao.entity.FieldInfo;
import com.common.dao.entity.TableInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapperUtils {

    /**
     *  key：类名
     *  value：表结构信息
     */
    private final static Map<String, TableInfo> entityTableInfoMap=new HashMap<>();

    /**
     *  key：Mapper 的 接口名
     *  value：mapper 对应的 泛型实体
     */
    private final static Map<String, Class<?>> entityClassMap=new HashMap<>();


    public static Class<?> getEntityClass(String name){
        return entityClassMap.get(name);
    }

    public static TableInfo getTableInfo(String name){
        return entityTableInfoMap.get(name);
    }

    public static void putEntity(String name,Class<?> entityClass){
        entityClassMap.put(name,entityClass);
    }

    public static void putTableInfo(String name,TableInfo tableInfo){
        entityTableInfoMap.put(name,tableInfo);
    }

    public static String getColumns(String name){
        TableInfo tableInfo = entityTableInfoMap.get(name);
        List<String> collect = tableInfo.getFieldMap().values().stream().map(FieldInfo::getColumnName).collect(Collectors.toList());
        return String.join(",", collect);
    }

}
