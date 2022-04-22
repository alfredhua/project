package com.common.mybatis.intercept.method;

import com.common.mybatis.entity.SqlParamInfo;
import com.common.mybatis.entity.WarpBoundSqlSqlSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

public abstract class AbstractBoundSql {


    /**
     * 抽象接口，主要为了拼接sql
     * @param mapperClassName
     * @param args
     * @return
     * @throws IllegalAccessException
     */
    public abstract SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args) throws  IllegalAccessException;

    /**
     * 主要将新拼接的sql和参数对象生成新的BoundSql,供Mybatis查询。
     * @param mapperClassName
     * @param args
     * @throws IllegalAccessException
     */
    public void executeNewSql(String mapperClassName,Object[] args) throws IllegalAccessException {
        SqlParamInfo sqlParamInfo = this.getSqlParamInfo(mapperClassName, args);
        MappedStatement mappedStatement = (MappedStatement)args[0];
        BoundSql oldBoundSql = mappedStatement.getBoundSql(args[1]);
        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), sqlParamInfo.getSql(),
                sqlParamInfo.getParameterMappings()==null?oldBoundSql.getParameterMappings():sqlParamInfo.getParameterMappings(),
                oldBoundSql.getParameterObject());
        // copy原始MappedStatement的各项属性
        MappedStatement.Builder builder = new MappedStatement.Builder(
                mappedStatement.getConfiguration(), mappedStatement.getId(),
                new WarpBoundSqlSqlSource(newBoundSql),
                mappedStatement.getSqlCommandType());

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



}