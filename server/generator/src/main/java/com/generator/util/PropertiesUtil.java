package com.generator.util;

/**
 * @author guozhenhua
 * @date 2019/08/27
 */
public class PropertiesUtil {

    // 根据命名规范，只修改此常量值即可
    public static String outPath= PropertiesFileUtil.getInstance("generator").get("outPath");   //输出路径
    public static String MODULE = PropertiesFileUtil.getInstance("generator").get("module");						   //模块名称
    public static String PACKAGE = PropertiesFileUtil.getInstance("generator").get("package");						   //模块名称
    public static String DATABASE =  PropertiesFileUtil.getInstance("generator").get("databse");				       //数据库名称
    public static String TABLE_PREFIX = PropertiesFileUtil.getInstance("generator").get("table_prefix");				   //表的前缀
    public static String PACKAGE_NAME = "com."+PACKAGE;		       //包
    public static boolean isBuildGradle=new Boolean(PropertiesFileUtil.getInstance("generator").get("isBuildGradle"));					//是否生成build.gradle文件
    public static boolean isNewFile=new Boolean(PropertiesFileUtil.getInstance("generator").get("isNewFile"));          //存在文件时是否替换：true：替换，false:不替换

    //api下的entity目录
    private static String apiPath=outPath+MODULE+"/" +MODULE+"-api/src/main/java/"+PACKAGE_NAME.replace(".", "/");

    public static String API_ENTITY=apiPath+"/entity/";

    //api下的dto目录
    public static String API_ENTITY_DTO=apiPath+"/dto/";

    //api下的service
    public static String SERVICE=apiPath+"/api/";

    //api下的constants
    public static String CONSTANTS=apiPath+"/constants/";


    private static String serverPath=outPath+MODULE+"/" +MODULE+"-server/src/main/java/"+PACKAGE_NAME.replace(".", "/");

    //server下的mapper
    public static String MAPPER=serverPath+"/dao/mapper";

    //server下的mapper_provider
    public static String MAPPER_PROVIDER=serverPath+"/dao/mapper";

    //server下的impl
    public static String SERVICE_IMPL=serverPath+"/impl";


    private static String controllerPath=outPath+MODULE+"/" +MODULE+"-web/src/main/java/com/web/controller/"+MODULE;
    //controller
    public static String CONTROLLER=controllerPath+"/";
    //vo
    public static String VO=controllerPath+"/vo/";

    /**
     * 数据库连接
     */
    public static String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
    public static String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
    public static String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
    public static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");


}
