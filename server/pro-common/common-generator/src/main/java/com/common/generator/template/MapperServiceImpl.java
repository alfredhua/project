package com.common.generator.template;

import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Map;

public class MapperServiceImpl extends GeneratorService {
    @Override
    public String getEntityVm() {
        return "/template/dao/Mapper.vm";
    }

    @Override
    public String getOutFilePath(String entity) {
        return "/mapper/"+entity+"Mapper.java";
    }

    @Override
    public VelocityContext getVelocityContext(Map<String, Object> map, List list) {
        return new VelocityContext();
    }
}
