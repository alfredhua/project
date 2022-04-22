package com.common.generator;

import com.common.generator.entity.JDBCEntity;
import com.common.generator.template.*;
import com.common.generator.util.QueryUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.common.generator.constants.PropertiesConstants.*;

public  class GeneratorApplication {


    static List<GeneratorService> list=new ArrayList<>(4);

    static {
        list.add(new InterfaceServiceImpl());
        list.add(new InterfaceImplServiceImpl());
        list.add(new MapperServiceImpl());
        list.add(new EntityServiceImpl());
    }

    public static void main(String[] args) {
        JDBCEntity jdbcEntity= JDBCEntity.getInstance().setJdbcDriver(JDBC_DRIVER).
                setJdbcPassword(JDBC_PASSWORD). setJdbcUseName(JDBC_USERNAME).
                setJdbcUrl(JDBC_URL);
        try {
            List<Map<String, Object>> tablesList = QueryUtil.getTables(jdbcEntity, DATABASE, TABLE_NAME);
            for (GeneratorService generatorService:list){
                generatorService.generator(jdbcEntity,tablesList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
