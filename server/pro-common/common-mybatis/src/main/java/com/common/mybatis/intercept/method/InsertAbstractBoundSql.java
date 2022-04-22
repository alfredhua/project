package com.common.mybatis.intercept.method;

import com.common.mybatis.entity.SqlParamInfo;
import com.common.mybatis.entity.TableInfo;
import com.common.mybatis.util.MapperEntityInfoUtils;
import com.common.mybatis.util.ParamInfoUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

public class InsertAbstractBoundSql  extends AbstractBoundSql {

    @Override
    public SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args){
        if (args.length>1) {
            TableInfo tableInfo = MapperEntityInfoUtils.getTableInfo(mapperClassName);
            List<ParameterMapping> list=new ArrayList<>();
            String s = new SQL() {{
                INSERT_INTO(tableInfo.getTableName());
                tableInfo.getFieldInfoList().forEach(fieldInfo -> {
                    VALUES(fieldInfo.getColumnName(), "?");
                    list.add(new ParameterMapping.Builder(ParamInfoUtils.getConfiguration(args),fieldInfo.getFiledName(),fieldInfo.getClazz()).build());
                });
            }}.toString();
            return new SqlParamInfo(s,list);
        }
        throw new RuntimeException("insert sql error");
    }

}
