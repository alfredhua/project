package com.generator.util;
import com.generator.entity.ColumnEntity;
import com.generator.entity.JDBCEntity;
import com.generator.entity.ModuleEntity;
import com.generator.entity.VmFileEntity;
import com.generator.sql.QueryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.generator.util.PropertiesUtil.*;

/**
 * @auth guozhenhua
 * @date 2018/12/06
 */
public class MybatisGeneratorUtil {



    public static void generator( JDBCEntity jdbcEntity ) throws Exception{

        VmFileEntity vmFileEntity = VmPathUtil.getVm();

        // 获取所有的当前数据库下的所有的table
        List<Map<String, Object>> tablesList = QueryUtil.getTables(jdbcEntity, DATABASE, TABLE_PREFIX);

        List<ModuleEntity> list=new ArrayList<>();
        // 生成api模块下的entity
        for (Map<String,Object> map:tablesList){

            List<ColumnEntity> listColumnEntity = QueryUtil.getColumns(jdbcEntity,DATABASE,map.get("table_name").toString());

            VelocityUtil.apiEntity(listColumnEntity,map.get("entity_name").toString(),vmFileEntity.getEntity_vm());
            // DTO下的实体类
            VelocityUtil.apiEntityDTO(map.get("entity_name").toString(),vmFileEntity.getEntity_dto_vm());

            VelocityUtil.apiConstants(map.get("entity_name").toString(),vmFileEntity.getConstants_vm());

            // service
            VelocityUtil.apiService(map.get("entity_name").toString(), vmFileEntity.getService_vm());

            // mapper
            VelocityUtil.serverMapper(map.get("table_name").toString(),map.get("entity_name").toString(),listColumnEntity,vmFileEntity.getMapper_vm());

            // provider
            VelocityUtil.serverMapperProvider(map.get("table_name").toString(),map.get("entity_name").toString(),vmFileEntity.getMapper_provider_vm(),listColumnEntity);
            // serviceImpl
            VelocityUtil.serverServiceImpl(map.get("entity_name").toString(),vmFileEntity.getService_impl_vm(),listColumnEntity);

            // controller
//            VelocityUtil.serverController(map.get("entity_name").toString(),map.get("table_name").toString(),vmFileEntity.getController_vm(),listColumnEntity);
//            VelocityUtil.serverVo(map.get("entity_name").toString(),vmFileEntity.getVo_vm());

            ModuleEntity moduleEntity=new ModuleEntity();
            moduleEntity.setUp_entity_name(map.get("table_name").toString().replace(TABLE_PREFIX,"").toUpperCase());
            moduleEntity.setLower_entity_name(map.get("table_name").toString().replace(TABLE_PREFIX,"").replace("_","-").toLowerCase());
            list.add(moduleEntity);
        }

        VelocityUtil.webModuleUrl(list,vmFileEntity.getModule_vm());


        // 生成build.gradle
        if (isBuildGradle) {
            VelocityUtil.serverBuild(vmFileEntity.getBuild_server_vm());
            VelocityUtil.apiBuild(vmFileEntity.getBuild_api_vm());
            VelocityUtil.webBuild(vmFileEntity.getBuild_web_vm());
        }



    }




}
