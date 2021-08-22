package com.generator.service;

import com.generator.util.DateTimeUtil;
import com.generator.util.StringUtil;
import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Map;

import static com.generator.util.PropertiesUtil.*;

public class ServerControllerService extends GeneratorService{
    @Override
    public String getEntityVm() {
        return "/template/server/controller/controller.vm";
    }

    @Override
    public String getEntityName(String entity) {
        return serverPath+"/service/controller/"+entity+"Controller.java";
    }

    @Override
    public VelocityContext getVelocityContext(Map<String, Object> map, List listColumnEntity) {
        String entityName = map.get("entity_name").toString();
        String tableName = map.get("table_name").toString();
        VelocityContext context = new VelocityContext();
        context.put("packageName", PACKAGE_NAME);
        context.put("entityName", entityName);
        context.put("lowerEntityName", entityName.toLowerCase());
        context.put("upperEntityName", tableName.replace(TABLE_PREFIX,"").toUpperCase());
        context.put("module", StringUtil.toUpperCaseFirstOne(MODULE));
        context.put("lowerModule",MODULE);
        context.put("columnEntityList", listColumnEntity);
        context.put("firstLowerEntityName",StringUtil.toLowerCaseFirstOne(entityName));
        context.put("ctime", DateTimeUtil.getDate());
        return context;
    }
}
