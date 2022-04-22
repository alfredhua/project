package com.common.mybatis.intercept.method;

import com.common.mybatis.entity.EntityWrapper;
import com.common.mybatis.entity.SqlParamInfo;
import com.common.mybatis.util.EntityWrapperUtils;
import com.common.mybatis.util.MapperEntityInfoUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

public class ListAllAbstractBoundSql extends AbstractBoundSql{

    @Override
    public SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args){
        Object object= args[1];
        if (object instanceof MapperMethod.ParamMap){
            MapperMethod.ParamMap<Object> map=(MapperMethod.ParamMap) object;
            EntityWrapper entityWrapper =(EntityWrapper) map.get("entityWrapper");
            List<ParameterMapping> list = new ArrayList<>();
            SQL sql = MapperEntityInfoUtils.getSelectSql(mapperClassName);
            EntityWrapperUtils.splicingConditionSql(sql,mapperClassName,entityWrapper,list,args);
            return new SqlParamInfo(sql.toString(),list);
        }
        throw new RuntimeException();
    }
}
