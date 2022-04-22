package com.common.generator.template;

import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Map;

public class EntityServiceImpl extends GeneratorService {
    @Override
    public String getEntityVm() {
        return "/template/entity/entity.vm";
    }

    @Override
    public String getOutFilePath(String entity) {
        return "/entity/"+entity+".java";
    }

    @Override
    public VelocityContext getVelocityContext(Map<String, Object> map, List list) {
        VelocityContext context = new VelocityContext();
        context.put("columnEntityList", list);
        return context;
    }
}
