package com.generator.service;

import com.generator.util.DateTimeUtil;
import com.generator.util.StringUtil;
import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Map;

import static com.generator.util.PropertiesUtil.*;

public class ServerControllerVoService extends GeneratorService{
    @Override
    public String getEntityVm() {
        return "/template/admin/controller/vo/vo.vm";
    }

    @Override
    public String getEntityName(String entity) {
        return adminPath+"/"+PACKAGE+"/vo/"+entity+"ReqVO.java";
    }

    @Override
    public VelocityContext getVelocityContext(Map<String, Object> map, List listColumnEntity) {
        String entityName = map.get("entity_name").toString();
        String name=entityName+"ReqVO";
        VelocityContext context = new VelocityContext();
        context.put("package", PACKAGE);
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
