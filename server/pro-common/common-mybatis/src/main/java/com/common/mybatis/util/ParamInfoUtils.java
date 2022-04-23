package com.common.mybatis.util;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;

import java.util.HashSet;

public class ParamInfoUtils {


    /**
     * 获取MappedStatement 中的config
     * @param args
     * @return
     */
    public static Configuration getConfiguration(Object[] args){
          MappedStatement mappedStatement = (MappedStatement) args[0];
          return mappedStatement.getConfiguration();
     }


    /**
     * 获取入参中的ids参数
     * @param arg
     * @return
     */
    public static String getParamsIds(Object arg){
        if (arg instanceof MapperMethod.ParamMap){
            MapperMethod.ParamMap map = (MapperMethod.ParamMap) arg;
            Object idsObject = map.get("ids");
            String ids="";
            if (ObjectUtils.isEmpty(idsObject)){
                return null;
            }
            if (idsObject instanceof HashSet){
                HashSet idSet=(HashSet) idsObject;
                if (idSet.isEmpty()){
                    throw new RuntimeException("params is empty");
                }
                for (Object id:idSet){
                    ids=ids+id+",";
                }
            }
            return ids.substring(0,ids.length()-1);
        }
        return "";
    }

}
