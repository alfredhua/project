package com.common.generator.template;

import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Map;

public class InterfaceImplServiceImpl extends GeneratorService {
    @Override
    public String getEntityVm() {
        return "/template/service/impl/impl.vm";
    }

    @Override
    public String getOutFilePath(String entity) {
        return "/service/impl/"+entity+"ServiceImpl.java";
    }

    @Override
    public VelocityContext getVelocityContext(Map<String, Object> map, List list) {
        return new VelocityContext();
    }
}
