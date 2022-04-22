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

public class ListByPageAbstractBoundSql extends AbstractBoundSql{

    @Override
    public SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args){
        Object object= args[1];
        if (object instanceof MapperMethod.ParamMap){
            MapperMethod.ParamMap<Object> map=(MapperMethod.ParamMap) object;
            Object page = map.get("page");
            Object pageSize = map.get("pageSize");
            EntityWrapper entityWrapper =(EntityWrapper) map.get("entityWrapper");

            List<ParameterMapping> list=new ArrayList<>();
            SQL sql = MapperEntityInfoUtils.getSelectSql(mapperClassName);
//            // 增加分页
            sql.LIMIT(String.valueOf(pageSize)).OFFSET((Long.parseLong(String.valueOf(page))-1)*Long.parseLong(String.valueOf(pageSize)));
            EntityWrapperUtils.splicingConditionSql(sql,mapperClassName,entityWrapper,list,args);


//            TableInfo tableInfo = MapperUtils.getTableInfo(mapperClassName);
//            SQL sql=new SQL().SELECT(getQueryColumns(tableInfo)) .FROM(tableInfo.getTableName());
//
//            List<ParameterMapping> list=new ArrayList<>();
//            List<Condition> conditions = entityWrapper.getList();
//            //条件拼接
//            List<Condition> collect = conditions.stream()
//                    .filter(item -> !item.getCondition().equals(ConditionEnum.orderBy)&& !ObjectUtils.isEmpty(item.getValue()) && !ObjectUtils.isEmpty(item.getColumn()))
//                    .collect(Collectors.toList());
//            collect.forEach(item->{
//                EntityWrapperUtils.getWhereSql(item,sql);
//                FieldInfo fieldInfo = tableInfo.getByColumnName(item.getColumn());
//                map.put(item.getColumn(),item.getValue());
//                list.add(new ParameterMapping.Builder(getConfiguration(args), fieldInfo.getColumnName(), fieldInfo.getClazz()).build());
//            });
//
//            // 增加分页
//            sql.LIMIT(String.valueOf(pageSize)).OFFSET((Long.parseLong(String.valueOf(page))-1)*Long.parseLong(String.valueOf(pageSize)));
//            // 排序
//            List<Condition> orderConditionList = entityWrapper.getList().stream()
//                    .filter(item -> item.getCondition().equals(ConditionEnum.orderBy) && !ObjectUtils.isEmpty(item.getValue()) && !ObjectUtils.isEmpty(item.getColumn()))
//                    .collect(Collectors.toList());
//            if (orderConditionList.isEmpty()){
//                orderConditionList.add(new Condition("create_time", ConditionEnum.orderBy, "DESC"));
//            }
//            orderConditionList.forEach(item->{
//                sql.ORDER_BY(item.getColumn()+" "+ item.getValue());
//            });
            return new SqlParamInfo(sql.toString(),list);
        }
        throw new RuntimeException();
    }


}
