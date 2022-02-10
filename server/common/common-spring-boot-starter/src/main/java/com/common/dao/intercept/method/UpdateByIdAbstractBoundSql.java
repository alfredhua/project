package com.common.dao.intercept.method;

import com.common.dao.entity.FieldInfo;
import com.common.dao.entity.SqlParamInfo;
import com.common.dao.entity.TableInfo;
import com.common.dao.util.MapperUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.ParameterMapping;
import org.springframework.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateByIdAbstractBoundSql extends AbstractBoundSql {


    @Override
    public SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args){
        if (args.length>1) {
            Object entity = args[1];
            TableInfo tableInfo = MapperUtils.getTableInfo(mapperClassName);
            BeanMap map = BeanMap.create(entity);
            List<ParameterMapping> parameterMappings=new ArrayList<>();
            String sql=new SQL(){{
                UPDATE(tableInfo.getTableName());
                for (Map.Entry<String, FieldInfo> entry : tableInfo.getFieldMap().entrySet()) {
                    String key = entry.getKey();
                    FieldInfo fieldInfo = entry.getValue();
                    Object value = map.get(key);
                    if (ObjectUtils.isEmpty(value)|| "id".equals(key)){
                        continue;
                    }
                    SET(fieldInfo.getColumnName() + "= ?");
                    parameterMappings.add(new ParameterMapping.Builder(getConfiguration(args),key,fieldInfo.getClazz()).build());
                }
                WHERE("id=?");
                parameterMappings.add(new ParameterMapping.Builder(getConfiguration(args),"id",Long.class).build());
            }}.toString();
            return new SqlParamInfo(sql,parameterMappings);
        }
        throw new RuntimeException("update by id sql error");
    }
}
