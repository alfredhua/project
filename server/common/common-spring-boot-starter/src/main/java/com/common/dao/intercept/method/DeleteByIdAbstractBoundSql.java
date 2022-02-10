package com.common.dao.intercept.method;

import com.common.dao.entity.SqlParamInfo;
import com.common.dao.entity.TableInfo;
import com.common.dao.util.MapperUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

public class DeleteByIdAbstractBoundSql extends AbstractBoundSql{

    @Override
    public SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args){
        if (args.length>1) {
            TableInfo tableInfo = MapperUtils.getTableInfo(mapperClassName);
            Object id= args[1];
            if (ObjectUtils.isEmpty(id)){
                throw new RuntimeException("deleteById the params is null");
            }
            String sql = new SQL().DELETE_FROM(tableInfo.getTableName()).WHERE(" id=?").toString();
            List<ParameterMapping> parameterMappings=new ArrayList<>();
            parameterMappings.add(new ParameterMapping.Builder(getConfiguration(args),"id",Long.class).build());
            return new SqlParamInfo(sql,parameterMappings);
        }
        throw new RuntimeException("delete by id sql error");
    }
}
