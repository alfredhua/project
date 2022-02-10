package com.common.dao.intercept.method;

import com.common.dao.entity.*;
import com.common.dao.enums.ConditionEnum;
import com.common.dao.util.EntityWrapperUtils;
import com.common.dao.util.MapperUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.ParameterMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllAbstractBoundSql extends AbstractBoundSql{

    @Override
    public SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args){
        Object object= args[1];
        if (object instanceof MapperMethod.ParamMap){
            MapperMethod.ParamMap<Object> map=(MapperMethod.ParamMap) object;
            EntityWrapper entityWrapper =(EntityWrapper) map.get("entityWrapper");
            TableInfo tableInfo = MapperUtils.getTableInfo(mapperClassName);
            SQL sql=new SQL().SELECT(getQueryColumns(tableInfo)) .FROM(tableInfo.getTableName());

            List<ParameterMapping> list=new ArrayList<>();
            List<Condition> conditions = entityWrapper.getList();
            //条件拼接
            List<Condition> collect = conditions.stream().filter(item -> !item.getCondition().equals(ConditionEnum.orderBy)).collect(Collectors.toList());
            collect.forEach(item->{
                EntityWrapperUtils.getWhereSql(item,sql);
                FieldInfo fieldInfo = tableInfo.getByColumnName(item.getColumn());
                map.put(item.getColumn(),item.getValue());
                list.add(new ParameterMapping.Builder(getConfiguration(args), fieldInfo.getColumnName(), fieldInfo.getClazz()).build());
            });

            // 排序
            List<Condition> orderConditionList = entityWrapper.getList().stream().filter(item -> item.getCondition().equals(ConditionEnum.orderBy)).collect(Collectors.toList());
            if (orderConditionList.isEmpty()){
                orderConditionList.add(new Condition("create_time", ConditionEnum.orderBy, "DESC"));
            }
            orderConditionList.forEach(item->{
                sql.ORDER_BY(item.getColumn()+" "+ item.getValue());
            });

            args[1]=map;
            return new SqlParamInfo(sql.toString(),list);
        }
        throw new RuntimeException();
    }
}
