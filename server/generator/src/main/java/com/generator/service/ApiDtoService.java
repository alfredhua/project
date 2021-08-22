package com.generator.service;

import com.generator.util.DateTimeUtil;
import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Map;

import static com.generator.util.PropertiesUtil.PACKAGE_NAME;

public class ApiDtoService extends GeneratorService{

    @Override
    public String getEntityVm() {
        return "/template/api/dto/dto.vm";
    }

    @Override
    public String getEntityName(String entity) {
        return apiPath+"/dto/"+entity+"ReqDTO.java";
    }

    @Override
    public VelocityContext getVelocityContext(Map<String, Object> map, List list) {
        String entityName = map.get("entity_name").toString();
        String name=entityName+"ReqDTO";
        VelocityContext context = new VelocityContext();
        context.put("packageName", PACKAGE_NAME+".dto");
        context.put("entityName", entityName);
        context.put("loseEntityName", entityName.toLowerCase());
        context.put("entityNameDTO", name);
        context.put("time",DateTimeUtil.getDate());
        return context;
    }
}
