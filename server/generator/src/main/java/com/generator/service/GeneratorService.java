package com.generator.service;

import com.generator.entity.ColumnEntity;
import com.generator.entity.JDBCEntity;
import com.generator.sql.QueryUtil;
import com.generator.util.PropertiesUtil;
import com.generator.util.VelocityUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.generator.util.PropertiesUtil.*;

public abstract class GeneratorService {

    public static String apiPath=outPath + MODULE+"/" + MODULE+"-api/src/main/java/"+PropertiesUtil.PACKAGE_NAME.replace(".", "/");

    public static String serverPath=outPath+MODULE+"/" +MODULE+"-server/src/main/java/"+PACKAGE_NAME.replace(".", "/");

    public static String adminPath=outPath+"web/web-admin/src/main/java/com/admin/controller/";

    public abstract String getEntityVm();

    public abstract String getEntityName(String entity);

    public abstract VelocityContext getVelocityContext(Map<String,Object> map, List list);

    public void generator( JDBCEntity jdbcEntity ,List<Map<String, Object>> tablesList) throws Exception {
        tablesList.forEach(map -> {
            try {
                List<ColumnEntity> listColumnEntity = QueryUtil.getColumns(jdbcEntity, DATABASE, map.get("table_name").toString());
                String outPutFile = getEntityName(map.get("entity_name").toString());
                File file=new File(outPutFile);
                if (!file.exists()){
                    String outPath=outPutFile.substring(0,outPutFile.lastIndexOf("/"));
                    File outFilePath = new File(outPath);
                    outFilePath.mkdirs();
                }
                String templateVM = VelocityUtil.class.getResource(getEntityVm()).getPath();
                generate(templateVM,outPutFile, getVelocityContext(map,listColumnEntity));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }



    /**
     * 根据模板生成文件
     * @param inputVmFilePath 模板路径
     * @param outputFilePath 输出文件路径
     * @param context
     * @throws Exception
     */
    public static void generate(String inputVmFilePath, String outputFilePath, VelocityContext context) throws Exception {
        try {
            Properties properties = new Properties();
            properties.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, getPath(inputVmFilePath));
            VelocityEngine velocityEngine = new VelocityEngine(properties);
            String file1 = getFile(inputVmFilePath);
            Template template = velocityEngine.getTemplate(file1,"utf-8");
            File file = new File(outputFilePath);
                if (!file.exists()){
                    //不存在就创建
                    file.createNewFile();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(file),"utf-8"));
                    template.merge(context, writer);
                    writer.close();
                }else{
                    //存在的话是否替换
                    if(PropertiesUtil.replace){
                        file.delete();
                        file.createNewFile();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream(file), "utf-8"));
                        template.merge(context, writer);
                        writer.close();
                    }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 根据文件绝对路径获取目录
     * @param filePath
     * @return
     */
    private static String getPath(String filePath) {
        String path = "";
        if (StringUtils.isNotBlank(filePath)) {
            path = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }
        return path;
    }

    /**
     * 根据文件绝对路径获取文件
     * @param filePath
     * @return
     */
    private static String getFile(String filePath) {
        String file = "";
        if (StringUtils.isNotBlank(filePath)) {
            file = filePath.substring(filePath.lastIndexOf("/") + 1);
        }
        return file;
    }

}
