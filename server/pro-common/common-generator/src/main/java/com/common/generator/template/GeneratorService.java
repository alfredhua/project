package com.common.generator.template;

import com.common.generator.entity.ColumnEntity;
import com.common.generator.entity.JDBCEntity;
import com.common.generator.constants.PropertiesConstants;
import com.common.generator.util.DateTimeUtil;
import com.common.generator.util.QueryUtil;
import com.common.generator.util.StringUtil;
import org.apache.commons.lang3.ObjectUtils;
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

import static com.common.generator.constants.PropertiesConstants.*;

public abstract class GeneratorService {

    public abstract String getEntityVm();

    public abstract String getOutFilePath(String entity);

    public abstract VelocityContext getVelocityContext(Map<String,Object> map, List list);

    public void generator(JDBCEntity jdbcEntity , List<Map<String, Object>> tablesList) throws Exception {
        tablesList.forEach(map -> {
            try {
                List<ColumnEntity> listColumnEntity = QueryUtil.getColumns(jdbcEntity, DATABASE, map.get("table_name").toString());
                String outFilePath=OUT_PATH+PACKAGE_NAME.replace(".","/")+getOutFilePath(map.get("entity_name").toString());;
                File file=new File(outFilePath);
                if (!file.exists()){
                    String outPath=outFilePath.substring(0,outFilePath.lastIndexOf("/"));
                    new File(outPath).mkdirs();
                }
                String templateVM = GeneratorService.class.getResource(getEntityVm()).getPath();
                generate(templateVM,outFilePath, initCommonVelocityContext(getVelocityContext(map,listColumnEntity),map));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private  VelocityContext initCommonVelocityContext(VelocityContext velocityContext,Map<String, Object> map){
        if (ObjectUtils.isEmpty(velocityContext)){
            return new VelocityContext();
        }
        String entityName = map.get("entity_name").toString();
        velocityContext.put("packageName", PACKAGE_NAME);  //包名
        velocityContext.put("date", DateTimeUtil.getDate());// 时间
        velocityContext.put("entityName", entityName);  //实体名
        velocityContext.put("firstLowerEntityName", StringUtil.toLowerCaseFirstOne(entityName));//首字母小写的实体名
        return velocityContext;
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
                return;
            }
            //存在的话是否替换
            if(PropertiesConstants.replace){
                file.delete();
                file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(file), "utf-8"));
                template.merge(context, writer);
                writer.close();
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
