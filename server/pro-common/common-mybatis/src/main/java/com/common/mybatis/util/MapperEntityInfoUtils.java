package com.common.mybatis.util;

import com.common.mybatis.entity.TableInfo;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Mapper 实体信息存储类，记录Mapper实体信息，以及获取各种方式的信息，辅助类，
 */
public class MapperEntityInfoUtils {

    /**
     *  key：类名
     *  value：表结构信息
     */
    private final static Map<String, TableInfo> entityTableInfoMap=new HashMap<>(64);

    public static TableInfo getTableInfo(String name){
        return entityTableInfoMap.get(name);
    }

    public static void putTableInfo(String name,TableInfo tableInfo){
        entityTableInfoMap.put(name,tableInfo);
    }

    /**
     * 查询列转换
     * @param tableInfo
     * @return
     */
    public static String getQueryColumns(TableInfo tableInfo){
        StringJoiner stringJoiner=new StringJoiner(",");
        tableInfo.getFieldInfoList().forEach(fieldInfo -> {
            stringJoiner.add("`"+fieldInfo.getColumnName()+"`" + " as `"+ fieldInfo.getFiledName()+"`");
        });
        return stringJoiner.toString();
    }

    /**
     * 查询sql拼接
     * @param mapperClassName
     * @return
     */
    public static SQL getSelectSql(String mapperClassName ){
        TableInfo tableInfo = entityTableInfoMap.get(mapperClassName);
        SQL sql=new SQL().SELECT(getQueryColumns(tableInfo)).FROM(tableInfo.getTableName());
        return sql;
    }

}
