package com.generator.service;

import com.generator.util.DateTimeUtil;
import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Map;

import static com.generator.util.PropertiesUtil.*;

public class ServerControllerListReqVoService extends GeneratorService{
    @Override
    public String getEntityVm() {
        return "/template/admin/controller/vo/vo.vm";
    }

    @Override
    public String getEntityName(String entity) {
        return adminPath+"/"+PACKAGE+"/vo/"+entity+"ListReqVO.java";
    }

    @Override
    public VelocityContext getVelocityContext(Map<String, Object> map, List listColumnEntity) {
        String entityName = map.get("entity_name").toString();
        String name=entityName+"ListReqVO";
        VelocityContext context = new VelocityContext();
        context.put("package", PACKAGE);
        context.put("packageName", PACKAGE_NAME);
        context.put("entityName", entityName);
        context.put("loseEntityName", entityName.toLowerCase());
        context.put("entityNameVO", name);
        context.put("module",MODULE);
        context.put("time",DateTimeUtil.getDate());
        context.put("lowerModule",MODULE.toLowerCase());
        context.put("isListReq",true);
        return context;
    }



}
