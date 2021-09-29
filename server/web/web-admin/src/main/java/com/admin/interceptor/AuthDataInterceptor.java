package com.admin.interceptor;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.auth.dto.LoginAdminRespDTO;
import com.common.aspect.annotation.DataAuth;
import com.common.middle.redis.RedisUtils;
import com.admin.constants.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author guozhenhua
 * @date 2021/05/25
 * SQL拦截器，主要处理数据权限拼接 where 的SQL 数据权限插件
 */
@Slf4j
@Intercepts(@Signature(type = Executor.class, method = "query",args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class AuthDataInterceptor implements Interceptor {

    private static String SYSTEM="SYSTEM";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        List<String> authCodeList = getAuthCodeList();
        //超级管理员直接执行
        if (authCodeList.contains(SYSTEM)){
            return invocation.proceed();
        }
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Class<?> classType = Class.forName(mappedStatement.getId().substring(0,mappedStatement.getId().lastIndexOf(".")));
        String mName  = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".") + 1);
        for(Method method : classType.getDeclaredMethods()){
            if(method.isAnnotationPresent(DataAuth.class) && mName.equals(method.getName()) ) {
                BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
                String newSql = getNewSql(boundSql, method.getAnnotation(DataAuth.class),authCodeList);
                execute(mappedStatement,newSql,boundSql,args);
                return invocation.proceed();
            }
        }
        return invocation.proceed();
//        }

    }

    private void execute(MappedStatement mappedStatement,String newSql, BoundSql boundSql,Object[] args){
        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), newSql,
                boundSql.getParameterMappings(), boundSql.getParameterObject());
        // copy原始MappedStatement的各项属性
        MappedStatement.Builder builder =
                new MappedStatement.Builder(mappedStatement.getConfiguration(), mappedStatement.getId(),
                        new WarpBoundSqlSqlSource(newBoundSql), mappedStatement.getSqlCommandType());
        builder.cache(mappedStatement.getCache()).databaseId(mappedStatement.getDatabaseId())
                .fetchSize(mappedStatement.getFetchSize())
                .flushCacheRequired(mappedStatement.isFlushCacheRequired())
                .keyColumn(StringUtils.join(mappedStatement.getKeyColumns(), ','))
                .keyGenerator(mappedStatement.getKeyGenerator())
                .keyProperty(StringUtils.join(mappedStatement.getKeyProperties(), ','))
                .lang(mappedStatement.getLang()).parameterMap(mappedStatement.getParameterMap())
                .resource(mappedStatement.getResource()).resultMaps(mappedStatement.getResultMaps())
                .resultOrdered(mappedStatement.isResultOrdered())
                .resultSets(StringUtils.join(mappedStatement.getResultSets(), ','))
                .resultSetType(mappedStatement.getResultSetType()).statementType(mappedStatement.getStatementType())
                .timeout(mappedStatement.getTimeout()).useCache(mappedStatement.isUseCache());
        MappedStatement newMappedStatement = builder.build();
        // 将新生成的MappedStatement对象替换到参数列表中
        args[0] = newMappedStatement;
    }

    static class WarpBoundSqlSqlSource implements SqlSource {
        private final BoundSql boundSql;
        public WarpBoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }
        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    private String getNewSql(BoundSql boundSql, DataAuth annotation, List<String> authCodeList){
        StringBuffer con=new StringBuffer();
        String sql = boundSql.getSql();
        if (authCodeList.isEmpty()) {
            con.append("'").append("'");
        }else{
            authCodeList.forEach(item -> con.append("'").append(item).append("'").append(","));
        }
        String condition= con.substring(0,con.toString().lastIndexOf(","));
        String column="auth_data_code";
        if (!"".equals(annotation.value())){
            column=annotation.value();
        }
        return SQLUtils.addCondition(sql, column + " in ("+condition+")", JdbcConstants.MYSQL);
    }

    private List<String> getAuthCodeList(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (ObjectUtils.isEmpty(requestAttributes)){
            return new ArrayList<>();
        }
        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes)requestAttributes;
        if (ObjectUtils.isEmpty(servletRequestAttributes)){
            return new ArrayList<>();
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader("token");
        if (ObjectUtils.isEmpty(token)){
            return new ArrayList<>();
        }
        LoginAdminRespDTO loginAdminRespDTO = RedisUtils.objectGet(CommonConstant.ADMIN_INFO.getKey() + token);
        if (ObjectUtils.isEmpty(loginAdminRespDTO)){
            return new ArrayList<>();
        }
        return loginAdminRespDTO.getAuth_code_list();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
