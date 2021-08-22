package com.generator.service;

import com.generator.util.DateTimeUtil;
import com.generator.util.StringUtil;
import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Map;

import static com.generator.util.PropertiesUtil.PACKAGE_NAME;

public class ServerInterfaceService extends GeneratorService {

    @Override
    public String getEntityVm() {
        return "/template/server/service/service.vm";
    }

    @Override
    public String getEntityName(String entity) {
        return serverPath+"/service/"+entity+"Interface.java";
    }

    @Override
    public VelocityContext getVelocityContext(Map<String, Object> map, List list) {
        String entityName = map.get("entity_name").toString();
        VelocityContext context = new VelocityContext();
        context.put("packageName", PACKAGE_NAME);
        context.put("entityName", entityName);
        context.put("lowerEntityName", entityName.toLowerCase());
        context.put("time", DateTimeUtil.getDate());
        context.put("firstLowerEntityName", StringUtil.toLowerCaseFirstOne(entityName));
        return context;
    }
}
