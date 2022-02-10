package com.common.dao.intercept.method;

import com.common.dao.entity.SqlParamInfo;
import com.common.dao.entity.TableInfo;
import com.common.dao.entity.WarpBoundSqlSqlSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractBoundSql {


    public Configuration getConfiguration(Object[] args){
          MappedStatement mappedStatement = (MappedStatement) args[0];
          return mappedStatement.getConfiguration();
     }

     public abstract SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args) throws  IllegalAccessException;

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


     public String getQueryColumns(TableInfo tableInfo){
          AtomicReference<String> columnsAtomic= new AtomicReference<>("");
          tableInfo.getFieldMap().forEach((key,fieldInfo)->{
               columnsAtomic.set(columnsAtomic + fieldInfo.getColumnName() + " as " + key + ",");
          });
          String columns = columnsAtomic.get();
          return columns.substring(0,columns.length()-1);
     }


     public String getIds(Object arg){
          if (arg instanceof MapperMethod.ParamMap){
               MapperMethod.ParamMap map = (MapperMethod.ParamMap) arg;
               Object idsObject = map.get("ids");
               String ids="";
               if (idsObject instanceof HashSet){
                    HashSet idSet=(HashSet) idsObject;
                    for (Object id:idSet){
                         ids=ids+id+",";
                    }
               }
               return ids.substring(0,ids.length()-1);
          }
          return "";
     }
}