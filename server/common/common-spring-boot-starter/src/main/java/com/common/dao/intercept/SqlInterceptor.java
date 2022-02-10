package com.common.dao.intercept;

import com.common.dao.intercept.method.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author guozhenhua
 * @date 2021/12/14
 * SQL拦截器
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query",args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update",args = {MappedStatement.class, Object.class})
})
public class SqlInterceptor implements Interceptor {

    private static Map<String, AbstractBoundSql> map = new HashMap<>();

    static {
        map.put("insert",new InsertAbstractBoundSql());
        map.put("updateById",new UpdateByIdAbstractBoundSql());
        map.put("queryById",new QueryByIdAbstractBoundSql());
        map.put("deleteById",new DeleteByIdAbstractBoundSql());
        map.put("listByIds",new ListByIdsAbstractBoundSql());
        map.put("deleteByIds",new DeleteByIdsAbstractBoundSql());
        map.put("listByPage",new ListByPageAbstractBoundSql());
        map.put("listAll",new ListAllAbstractBoundSql());
    }

    private static Map<String, Class> entityClassMap = new HashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        String methodName  = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".") + 1);
        if (!map.containsKey(methodName)){
            return invocation.proceed();
        }
        // 获取mapper的接口Class
        String mapperClassName = mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf("."));
        MapperRegistry mapperRegistry = mappedStatement.getConfiguration().getMapperRegistry();
        Class<?> mapperClass = getMapperClass(mapperRegistry.getMappers(), mapperClassName);
        // 获取对应的执行的方法
        Method[] methods = mapperClass.getMethods();
        Method method = Arrays.stream(methods).filter(item -> item.getName().equals(methodName)).findFirst().orElse(null);
        if (ObjectUtils.isEmpty(method)){
            return invocation.proceed();
        }
        // 修改sql
        map.get(method.getName()).executeNewSql(mapperClassName,args);
        return invocation.proceed();
    }

    private Class<?> getMapperClass(Collection<Class<?>> mappers,String mapperName){
        return mappers.stream().filter(item -> item.getName().equals(mapperName)).findFirst().orElse(null);
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
