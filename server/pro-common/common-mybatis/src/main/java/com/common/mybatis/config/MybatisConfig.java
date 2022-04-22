package com.common.mybatis.config;

import com.common.mybatis.BaseMapper;
import com.common.mybatis.annotation.Column;
import com.common.mybatis.annotation.Table;
import com.common.mybatis.entity.FieldInfo;
import com.common.mybatis.entity.TableInfo;
import com.common.mybatis.util.MapperEntityInfoUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class MybatisConfig {

    @Autowired(required = false)
    List<BaseMapper<?>> baseMapperList;

    public void init(){
        if (ObjectUtils.isEmpty(baseMapperList)){
            return;
        }
        baseMapperList.forEach(this::initMapperEntityInfoUtils);
    }


    private void initMapperEntityInfoUtils(BaseMapper<?> baseMapper){
        try {
            Class<?>[] interfaces = baseMapper.getClass().getInterfaces();
            Class<?> mapperClass = interfaces[0];
            String mapperClassName = mapperClass.getName();
            Type[] genericInterfaces = mapperClass.getGenericInterfaces();
            ParameterizedType type = (ParameterizedType) genericInterfaces[0];
            Class<?> entityClass = Class.forName(type.getActualTypeArguments()[0].getTypeName());
            MapperEntityInfoUtils.putTableInfo(mapperClassName,getTableInfo(entityClass));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private TableInfo getTableInfo(Class<?> entityClass){
        TableInfo tableInfo=new TableInfo();
        Table table = entityClass.getAnnotation(Table.class);
        if (ObjectUtils.isEmpty(table) || ObjectUtils.isEmpty(table.value())){
            throw new RuntimeException("class "+entityClass+ " annotation table is empty");
        }
        tableInfo.setTableName(table.value());
        tableInfo.setFieldInfoList(getEntityClassFields(entityClass));
        return tableInfo;
    }

    private List<FieldInfo> getEntityClassFields(Class<?> entityClass){
        Class<?> superclass = entityClass.getSuperclass();
        Field[] superFields=null;
        if (!ObjectUtils.isEmpty(superclass)){
            superFields= superclass.getDeclaredFields();
        }
        Field[] fields = ArrayUtils.addAll(superFields, entityClass.getDeclaredFields());
        return Arrays.stream(fields).map(field -> {
            field.setAccessible(true);
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setFiledName(field.getName());
            Column annotation = field.getAnnotation(Column.class);
            if (!ObjectUtils.isEmpty(annotation)) {
                fieldInfo.setColumnName(annotation.value());
            }else{
                fieldInfo.setColumnName(field.getName());
            }
            fieldInfo.setClazz(field.getType());
            return fieldInfo;
        }).collect(Collectors.toList());
    }

}
