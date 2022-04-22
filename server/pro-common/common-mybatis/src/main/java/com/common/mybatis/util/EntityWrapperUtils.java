package com.common.mybatis.util;

import com.common.mybatis.entity.Condition;
import com.common.mybatis.entity.EntityWrapper;
import com.common.mybatis.entity.FieldInfo;
import com.common.mybatis.entity.TableInfo;
import com.common.mybatis.enums.ConditionEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.ParameterMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 查询条件参数辅助类，生成带有条件的sql信息。
 */
public class EntityWrapperUtils {


    private static final Map<ConditionEnum,String> map=new HashMap<>(8);

    static {
        map.put(ConditionEnum.eq,"=");
        map.put(ConditionEnum.le,"<=");
        map.put(ConditionEnum.lt,"<");
        map.put(ConditionEnum.gt,">");
        map.put(ConditionEnum.ge,">=");
    }

    public static SQL getWhereSql(Condition condition, SQL sql){
        if (map.containsKey(condition.getCondition())){
            return sql.WHERE(condition.getColumn()+" "+map.get(condition.getCondition())+" ? ");
        }
        if (condition.getCondition().equals(ConditionEnum.like)){
            return sql.WHERE(condition.getColumn()+" like CONCAT('%',?,'%') ");
        }
        return sql;
    }


    /**
     * 拼接list查询的条件,以及组装信息
     * @param sql
     * @param entityWrapper
     * @param list
     */
    public static void splicingConditionSql(SQL sql,String mapperClassName, EntityWrapper entityWrapper,  List<ParameterMapping> list,Object[] args){
        TableInfo tableInfo = MapperEntityInfoUtils.getTableInfo(mapperClassName);
        MapperMethod.ParamMap<Object> map = (MapperMethod.ParamMap) args[1];
        //条件拼接
        List<Condition> collect = entityWrapper.getList().stream()
                .filter(item -> !item.getCondition().equals(ConditionEnum.orderBy) && !ObjectUtils.isEmpty(item.getValue()) && !ObjectUtils.isEmpty(item.getColumn()) )
                .collect(Collectors.toList());
        collect.forEach(item->{
            EntityWrapperUtils.getWhereSql(item,sql);
            FieldInfo fieldInfo = tableInfo.getByColumnName(item.getColumn());
            map.put(item.getColumn(),item.getValue());
            list.add(new ParameterMapping.Builder(ParamInfoUtils.getConfiguration(args), fieldInfo.getColumnName(), fieldInfo.getClazz()).build());
        });
        // 排序
        List<Condition> orderConditionList = entityWrapper.getList().stream()
                .filter(item -> item.getCondition().equals(ConditionEnum.orderBy) && !ObjectUtils.isEmpty(item.getValue()) && !ObjectUtils.isEmpty(item.getColumn()) )
                .collect(Collectors.toList());
        if (orderConditionList.isEmpty()){
            orderConditionList.add(new Condition("create_time", ConditionEnum.orderBy, "DESC"));
        }
        orderConditionList.forEach(item->{
            sql.ORDER_BY(item.getColumn()+" "+ item.getValue());
        });
    }

//
//    public void a(){
//                //条件拼接
//        List<Condition> collect = entityWrapper.getList().stream()
//                .filter(item -> !item.getCondition().equals(ConditionEnum.orderBy) && !ObjectUtils.isEmpty(item.getValue()) && !ObjectUtils.isEmpty(item.getColumn()) )
//                .collect(Collectors.toList());
//        collect.forEach(item->{
//            EntityWrapperUtils.getWhereSql(item,sql);
//            FieldInfo fieldInfo = tableInfo.getByColumnName(item.getColumn());
//            map.put(item.getColumn(),item.getValue());
//            list.add(new ParameterMapping.Builder(mappedStatement.getConfiguration(), fieldInfo.getColumnName(), fieldInfo.getClazz()).build());
//        });
//        // 排序
//        List<Condition> orderConditionList = entityWrapper.getList().stream()
//                .filter(item -> item.getCondition().equals(ConditionEnum.orderBy) && !ObjectUtils.isEmpty(item.getValue()) && !ObjectUtils.isEmpty(item.getColumn()) )
//                .collect(Collectors.toList());
//        if (orderConditionList.isEmpty()){
//            orderConditionList.add(new Condition("create_time", ConditionEnum.orderBy, "DESC"));
//        }
//        orderConditionList.forEach(item->{
//            sql.ORDER_BY(item.getColumn()+" "+ item.getValue());
//        });
//    }
}
