package com.common.generator.template;

import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Map;

public class InterfaceServiceImpl extends GeneratorService {

    @Override
    public String getEntityVm() {
        return "/template/service/service.vm";
    }

    @Override
    public String getOutFilePath(String entityName) {
        return "/service/"+entityName+"Service.java";
    }

    @Override
    public VelocityContext getVelocityContext(Map<String, Object> map, List list) {
        return new VelocityContext();
    }

}
