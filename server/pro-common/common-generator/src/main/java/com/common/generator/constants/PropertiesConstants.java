package com.common.generator.constants;

import com.common.generator.util.PropertiesFileUtil;

/**
 * @author guozhenhua
 * @date 2019/08/27
 */
public class PropertiesConstants {
    /**
     * 数据库连接
     */
    public static String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
    public static String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
    public static String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
    public static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");
    public static String DATABASE =  PropertiesFileUtil.getInstance("generator").get("databse");				       //数据库名称
    public static String TABLE_PREFIX = PropertiesFileUtil.getInstance("generator").get("table_prefix");
    public static String TABLE_NAME = PropertiesFileUtil.getInstance("generator").get("table_name");

    // 根据命名规范，只修改此常量值即可
    public static String OUT_PATH= PropertiesFileUtil.getInstance("generator").get("outPath");   //输出路径
    public static String MODULE = PropertiesFileUtil.getInstance("generator").get("module");						   //模块名称
    public static String PACKAGE_NAME = PropertiesFileUtil.getInstance("generator").get("package");						   //模块名称
    public static boolean replace=new Boolean(PropertiesFileUtil.getInstance("generator").get("replace"));          //存在文件时是否替换：true：替换，false:不替换

}
