package com.common.mybatis.intercept.method;

import com.common.mybatis.entity.EntityWrapper;
import com.common.mybatis.entity.SqlParamInfo;
import com.common.mybatis.entity.TableInfo;
import com.common.mybatis.util.EntityWrapperUtils;
import com.common.mybatis.util.MapperEntityInfoUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

public class ListCountAbstractBoundSql extends AbstractBoundSql{

    @Override
    public SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args){
        Object object= args[1];
        if (object instanceof MapperMethod.ParamMap){
            MapperMethod.ParamMap<Object> map=(MapperMethod.ParamMap) object;
            Object entityWrapperObject = map.get("entityWrapper");
            EntityWrapper entityWrapper=null;
            if (!ObjectUtils.isEmpty(entityWrapperObject)){
                entityWrapper=(EntityWrapper)entityWrapperObject ;
            }
            List<ParameterMapping> list = new ArrayList<>();
            TableInfo tableInfo = MapperEntityInfoUtils.getTableInfo(mapperClassName);
            SQL sql = new SQL() {{
                SELECT("count(*)");
                FROM(tableInfo.getTableName());
            }};
            EntityWrapperUtils.splicingConditionSql(sql,mapperClassName,entityWrapper,list,args);
            return new SqlParamInfo(sql.toString(),list);
        }
        throw new RuntimeException();
    }
}
