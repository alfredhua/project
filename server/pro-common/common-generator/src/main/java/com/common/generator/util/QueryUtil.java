package com.common.generator.util;

import com.common.generator.entity.ColumnEntity;
import com.common.generator.entity.JDBCEntity;
import org.apache.commons.lang.ObjectUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.common.generator.constants.PropertiesConstants.TABLE_PREFIX;
import static com.common.generator.util.StringUtil.lineToHump;

/**
 * @auth guozhenhua
 * @date 2018/12/06
 */
public class QueryUtil {


    /**
     * 查询数据库表
     * @param jdbcEntity
     * @param database
     * @param tablePrefix
     * @return
     * @throws SQLException
     */
    public static List<Map<String,Object>> getTables(JDBCEntity jdbcEntity, String database, String tablePrefix) throws SQLException {
        List<Map<String, Object>> tables = new ArrayList<>();
        Map<String, Object> table;
        JdbcUtil jdbcUtil = new JdbcUtil(jdbcEntity);
        List<Map> result = jdbcUtil.selectByParams(SQLUtil.selectTableNameSql(database,tablePrefix ), null);
        for (Map map : result) {
            String tableName=ObjectUtils.toString(map.get("TABLE_NAME"));
            table = new HashMap<>(2);
            table.put("table_name",tableName);
            String preEntityName = tableName.replace(TABLE_PREFIX, "");
            table.put("entity_name", lineToHump(preEntityName));
            tables.add(table);
        }
        jdbcUtil.release();
        return tables;
    }

//
    public static List<ColumnEntity> getColumns(JDBCEntity jdbcEntity, String database, String tableName)
                                                throws SQLException {
        List<ColumnEntity> list = new ArrayList<>();
        JdbcUtil jdbcUtil = new JdbcUtil(jdbcEntity);
        List<Map> result = jdbcUtil.selectByParams(SQLUtil.selectColumnSql(database,tableName ), null);
        for (Map map : result) {
            ColumnEntity columnEntity=new ColumnEntity();
            String tableColumnName=map.get("COLUMN_NAME")!=null?String.valueOf(map.get("COLUMN_NAME")):"";
            columnEntity.setTableColumnName(tableColumnName);
            columnEntity.setType(map.get("DATA_TYPE")!=null?String.valueOf(map.get("DATA_TYPE")):"");
            columnEntity.setComment(map.get("COLUMN_COMMENT")!=null?String.valueOf(map.get("COLUMN_COMMENT")):"");
            columnEntity.setHumpTableColumnName(StringUtil.toUpperCaseFirstOne(tableColumnName));
            String s = lineToHump(tableColumnName);
            columnEntity.setHumpName(s);
            columnEntity.setName(StringUtil.toLowerCaseFirstOne(s));
            list.add(columnEntity);
        }
        return list;

    }



}
