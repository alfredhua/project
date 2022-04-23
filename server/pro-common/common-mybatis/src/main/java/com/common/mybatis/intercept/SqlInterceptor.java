package com.common.mybatis.intercept;

import com.common.mybatis.intercept.method.*;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
        map.put("listCount",new ListCountAbstractBoundSql());
    }
    private static Map<String, Class> entityClassMap = new HashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        String methodName  = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".") + 1);
        // 不存在通用的sql，继续执行
        if (!map.containsKey(methodName)){
            return invocation.proceed();
        }
        String mapperClassName = mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf("."));
        MapperRegistry mapperRegistry = mappedStatement.getConfiguration().getMapperRegistry();
        Class<?> mapperClass = mapperRegistry.getMappers().stream()
                .filter(item -> item.getName().equals(mapperClassName)).findFirst().orElseThrow(()-> new RuntimeException("mapper is empty"));
        // 修改sql
        map.get(methodName).executeNewSql(mapperClass.getName(),args);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
