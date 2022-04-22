package com.common.mybatis.intercept.method;

import com.common.mybatis.entity.SqlParamInfo;
import com.common.mybatis.entity.TableInfo;
import com.common.mybatis.util.MapperEntityInfoUtils;
import com.common.mybatis.util.ParamInfoUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

public class DeleteByIdAbstractBoundSql extends AbstractBoundSql{

    @Override
    public SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args){
        if (args.length>1) {
            TableInfo tableInfo = MapperEntityInfoUtils.getTableInfo(mapperClassName);
            Object id= args[1];
            if (ObjectUtils.isEmpty(id)){
                throw new RuntimeException("deleteById the params is null");
            }
            String sql = new SQL().DELETE_FROM(tableInfo.getTableName()).WHERE(" id=?").toString();
            List<ParameterMapping> parameterMappings=new ArrayList<>();
            parameterMappings.add(new ParameterMapping.Builder(ParamInfoUtils.getConfiguration(args),"id",Long.class).build());
            return new SqlParamInfo(sql,parameterMappings);
        }
        throw new RuntimeException("delete by id sql error");
    }
}
