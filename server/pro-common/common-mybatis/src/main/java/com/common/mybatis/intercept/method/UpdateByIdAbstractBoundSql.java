package com.common.mybatis.intercept.method;

import com.common.mybatis.entity.SqlParamInfo;
import com.common.mybatis.entity.TableInfo;
import com.common.mybatis.util.MapperEntityInfoUtils;
import com.common.mybatis.util.ParamInfoUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.ParameterMapping;
import org.springframework.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.List;

public class UpdateByIdAbstractBoundSql extends AbstractBoundSql {


    @Override
    public SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args){
        if (args.length>1) {
            BeanMap map = BeanMap.create(args[1]);
            List<ParameterMapping> parameterMappings=new ArrayList<>();
            TableInfo tableInfo = MapperEntityInfoUtils.getTableInfo(mapperClassName);
            String sql=new SQL(){{
                UPDATE(tableInfo.getTableName());
                tableInfo.getFieldInfoList().forEach(fieldInfo->{
                    if (!ObjectUtils.isEmpty(map.get(fieldInfo.getFiledName())) && !"id".equals(fieldInfo.getColumnName())){
                        SET("`"+fieldInfo.getColumnName()+"`" + "= ?");
                        parameterMappings.add(new ParameterMapping.Builder(ParamInfoUtils.getConfiguration(args),fieldInfo.getFiledName(),fieldInfo.getClazz()).build());
                    }
                });
                WHERE("id=?");
                parameterMappings.add(new ParameterMapping.Builder(ParamInfoUtils.getConfiguration(args),"id",Long.class).build());
            }}.toString();
            return new SqlParamInfo(sql,parameterMappings);
        }
        throw new RuntimeException("update by id sql error");
    }
}
