package com.generator.sql;

/**
 * @auth guozhenhua
 * @date 2018/12/06
 */
public class SQLUtil {


    /**
     * 获取查询数据库表名的sql
     * @param database
     * @param tablePrefix
     * @return
     */
    public static String selectTableNameSql(String database, String tablePrefix){
        return "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "' AND table_name LIKE '" + tablePrefix + "%';";
    }

    public static  String selectColumnSql(String database,String tableName){
        return "SELECT COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = '"+database+"' AND TABLE_NAME = '"+tableName+"'";
    }



}
