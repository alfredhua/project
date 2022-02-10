package com.common.dao.intercept.method;

import com.common.dao.entity.SqlParamInfo;
import com.common.dao.entity.TableInfo;
import com.common.dao.util.MapperUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

public class InsertAbstractBoundSql  extends AbstractBoundSql {

    @Override
    public SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args){
        if (args.length>1) {
            TableInfo tableInfo = MapperUtils.getTableInfo(mapperClassName);
            List<ParameterMapping> list=new ArrayList<>();
            String s = new SQL() {{
                INSERT_INTO(tableInfo.getTableName());
                tableInfo.getFieldMap().forEach((key, fieldInfo) -> {
                    VALUES(fieldInfo.getColumnName(), "?");
                    list.add(new ParameterMapping.Builder(getConfiguration(args),key,fieldInfo.getClazz()).build());
                });
            }}.toString();
            return new SqlParamInfo(s,list);
        }
        throw new RuntimeException("insert sql error");
    }

}
