package com.common.mybatis.entity;

import lombok.Data;
import org.apache.ibatis.mapping.ParameterMapping;

import java.util.List;

@Data
public class SqlParamInfo {

    String sql;

    List<ParameterMapping> parameterMappings;

    Object parameterObject;

    public SqlParamInfo(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.parameterObject=null;
    }

    public SqlParamInfo(String sql, List<ParameterMapping> parameterMappings, Object parameterObject) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.parameterObject = parameterObject;
    }
}
