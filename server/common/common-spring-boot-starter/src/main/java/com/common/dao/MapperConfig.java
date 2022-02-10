package com.common.dao;

import com.common.dao.annotation.Column;
import com.common.dao.annotation.Table;
import com.common.dao.entity.FieldInfo;
import com.common.dao.entity.TableInfo;
import com.common.dao.typeHandler.LocalDateTimeTypeHandler;
import com.common.dao.typeHandler.LocalDateTypeHandler;
import com.common.dao.util.MapperUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class MapperConfig implements ApplicationContextAware {

    @Autowired
    List<BaseMapper> baseMapperList;

    @Bean
    public Map<String,String> initMapperInfo(){
        baseMapperList.forEach(this::initMapperUtil);
        return new HashMap<>();
    }

    private void initMapperUtil(BaseMapper baseMapper){
        try {
            Class<?>[] interfaces = baseMapper.getClass().getInterfaces();
            Class<?> mapperClass = interfaces[0];
            String mapperClassName = mapperClass.getName();
            Type[] genericInterfaces = mapperClass.getGenericInterfaces();
            ParameterizedType type = (ParameterizedType) genericInterfaces[0];
            Class<?> entityClass = Class.forName(type.getActualTypeArguments()[0].getTypeName());
            MapperUtils.putEntity(mapperClassName, entityClass);
            initTableInfo(mapperClassName,entityClass);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getTableName(Class o){
        Table table =(Table) o.getAnnotation(Table.class);
        if (ObjectUtils.isEmpty(table)){
            throw new RuntimeException("annotation table is empty");
        }
        if (ObjectUtils.isEmpty(table.value())){
            throw new RuntimeException("table name value is empty");
        }
        return table.value();
    }

    private void initTableInfo(String mapperClassName,Class<?> entityClass){
        TableInfo tableInfo=new TableInfo();
        tableInfo.setTableName(getTableName(entityClass));
        tableInfo.setFieldMap(getFieldMap(entityClass));
        MapperUtils.putTableInfo(mapperClassName,tableInfo);
    }

     private Map<String, FieldInfo> getFieldMap(Class<?> entityClass){
         Map<String, FieldInfo> map=new HashMap<>();
         Class<?> superclass = entityClass.getSuperclass();
         Field[] superFields=null;
         if (!ObjectUtils.isEmpty(superclass)){
             superFields= superclass.getDeclaredFields();
         }
         Field[] fields = ArrayUtils.addAll(superFields, entityClass.getDeclaredFields());
         for (Field field:fields) {
             field.setAccessible(true);
             FieldInfo fieldInfo = new FieldInfo();
             Column annotation = field.getAnnotation(Column.class);
             if (!ObjectUtils.isEmpty(annotation)){
                 fieldInfo.setColumnName(annotation.value());
             }
             fieldInfo.setClazz(field.getType());
             map.put(field.getName(),fieldInfo);
          }
          return map;
     }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SqlSessionFactory sqlSessionFactory = applicationContext.getBean(SqlSessionFactory.class);
        TypeHandlerRegistry typeHandlerRegistry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
        typeHandlerRegistry.register(LocalDateTime.class, LocalDateTimeTypeHandler.class);
        typeHandlerRegistry.register(LocalDate.class, LocalDateTypeHandler.class);
    }

}
