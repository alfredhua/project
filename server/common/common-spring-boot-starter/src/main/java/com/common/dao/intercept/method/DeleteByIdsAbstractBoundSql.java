package com.common.dao.intercept.method;

import com.common.dao.entity.SqlParamInfo;
import com.common.dao.entity.TableInfo;
import com.common.dao.util.MapperUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.jdbc.SQL;

public class DeleteByIdsAbstractBoundSql extends AbstractBoundSql{

    @Override
    public SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args) {
        if (args.length>1) {
            Object arg = args[1];
            if (ObjectUtils.isEmpty(arg)){
                throw new RuntimeException("deleteByIds the params is null");
            }
            TableInfo tableInfo = MapperUtils.getTableInfo(mapperClassName);
            SQL sql = new SQL().DELETE_FROM(tableInfo.getTableName()).WHERE("id in ("+getIds(arg)+")");
            return new SqlParamInfo(sql.toString(),null);
        }
        throw new RuntimeException("delete by ids sql error");
    }

}
