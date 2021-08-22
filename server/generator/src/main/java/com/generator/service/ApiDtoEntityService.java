package com.generator.service;

import com.generator.util.DateTimeUtil;
import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Map;

import static com.generator.util.PropertiesUtil.PACKAGE_NAME;

public class ApiDtoEntityService extends GeneratorService{

    @Override
    public String getEntityVm() {
        return "/template/api/dto/entity/entity.vm";
    }

    @Override
    public String getEntityName(String entity) {
        return apiPath+"/dto/entity/"+entity+".java";
    }

    @Override
    public VelocityContext getVelocityContext(Map<String, Object> map, List list) {
        VelocityContext context = new VelocityContext();
        context.put("packageName", PACKAGE_NAME+".dto.entity");
        context.put("entityName", map.get("entity_name").toString());
        context.put("entity", "entity");
        context.put("columnEntityList", list);
        context.put("time", DateTimeUtil.getDate());
        return context;
    }
}
