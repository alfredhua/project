package com.generator.service;

import com.generator.util.DateTimeUtil;
import com.generator.util.StringUtil;
import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Map;

import static com.generator.util.PropertiesUtil.DATABASE;
import static com.generator.util.PropertiesUtil.PACKAGE_NAME;

public class ServerMapperService extends GeneratorService{

    @Override
    public String getEntityVm() {
        return "/template/server/dao/mapper.vm";
    }

    @Override
    public String getEntityName(String entity) {
        return serverPath+"/dao/"+entity+"Mapper.java";
    }

    @Override
    public VelocityContext getVelocityContext(Map<String,Object> map, List listColumnEntity) {
        String entityName = map.get("entity_name").toString();
        String tableName = map.get("table_name").toString();
        VelocityContext context = new VelocityContext();
        context.put("packageName", PACKAGE_NAME);
        context.put("entityName", entityName);
        context.put("lowerEntityName", entityName.toLowerCase());
        context.put("database", DATABASE);
        context.put("columnEntityList", listColumnEntity);
        context.put("tableName", tableName);
        context.put("firstLowerEntityName", StringUtil.toLowerCaseFirstOne(entityName));
        context.put("ctime",DateTimeUtil.getDate());
        return context;
    }
}
