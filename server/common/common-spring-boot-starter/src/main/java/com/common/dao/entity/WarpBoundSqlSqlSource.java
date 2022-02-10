package com.common.dao.entity;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;

public class WarpBoundSqlSqlSource implements SqlSource {

    private final BoundSql boundSql;

    public WarpBoundSqlSqlSource(BoundSql boundSql) {
        this.boundSql = boundSql;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return boundSql;
    }

}
