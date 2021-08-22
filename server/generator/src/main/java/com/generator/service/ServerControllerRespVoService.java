package com.generator.service;

import com.generator.util.DateTimeUtil;
import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Map;

import static com.generator.util.PropertiesUtil.MODULE;
import static com.generator.util.PropertiesUtil.PACKAGE_NAME;

public class ServerControllerRespVoService extends GeneratorService{
    @Override
    public String getEntityVm() {
        return "/template/server/controller/controller.vm";
    }

    @Override
    public String getEntityName(String entity) {
        return serverPath+"/service/controller/vo/"+entity+"RespVO.java";
    }

    @Override
    public VelocityContext getVelocityContext(Map<String, Object> map, List listColumnEntity) {
        String entityName = map.get("entity_name").toString();
        String name=entityName+"RespVO";
        VelocityContext context = new VelocityContext();
        context.put("packageName", PACKAGE_NAME);
        context.put("entityName", entityName);
        context.put("loseEntityName", entityName.toLowerCase());
        context.put("entityNameVO", name);
        context.put("module",MODULE);
        context.put("time",DateTimeUtil.getDate());
        context.put("lowerModule",MODULE.toLowerCase());
        return context;
    }



}
