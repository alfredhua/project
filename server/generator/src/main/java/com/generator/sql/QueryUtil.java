package com.generator.sql;

import com.generator.entity.ColumnEntity;
import com.generator.entity.JDBCEntity;
import com.generator.util.JdbcUtil;
import com.generator.util.StringUtil;
import org.apache.commons.lang.ObjectUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.generator.util.StringUtil.lineToHump;

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
            System.out.println(map.get("TABLE_NAME"));
            table = new HashMap<>(2);
            table.put("table_name", map.get("TABLE_NAME"));
            String preEntityName=ObjectUtils.toString(map.get("TABLE_NAME"));
            String entityName = preEntityName.substring(tablePrefix.length());
            table.put("entity_name", lineToHump(entityName));
            tables.add(table);
        }
        jdbcUtil.release();
        return tables;
    }

//
    public static List<ColumnEntity> getColumns(JDBCEntity jdbcEntity, String database,String tableName)
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
            String s = StringUtil.lineToHump(tableColumnName);
            columnEntity.setHumpName(s);
            columnEntity.setName(StringUtil.toLowerCaseFirstOne(s));
            list.add(columnEntity);
        }
        return list;

    }



}
