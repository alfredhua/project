package com.common.mybatis.intercept.method;

import com.common.mybatis.entity.SqlParamInfo;
import com.common.mybatis.entity.TableInfo;
import com.common.mybatis.util.MapperEntityInfoUtils;
import com.common.mybatis.util.ParamInfoUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.ParameterMapping;

import java.util.List;
import java.util.stream.Collectors;

public class QueryByIdAbstractBoundSql extends AbstractBoundSql{

    @Override
    public SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args) {
        if (args.length>1) {
            TableInfo tableInfo = MapperEntityInfoUtils.getTableInfo(mapperClassName);
            List<ParameterMapping> parameterMappingList = tableInfo.getFieldInfoList().stream().filter(item-> "id".equals(item.getColumnName()))
                    .map(item -> new ParameterMapping.Builder(ParamInfoUtils.getConfiguration(args), item.getFiledName(), item.getClazz()).build())
                    .collect(Collectors.toList());
            String s = new SQL().FROM(tableInfo.getTableName()).SELECT(MapperEntityInfoUtils.getQueryColumns(tableInfo)).WHERE("id=?").toString();
            return new SqlParamInfo(s,parameterMappingList);
        }
        throw new RuntimeException("queryById error");
    }
}
