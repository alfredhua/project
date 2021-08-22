package com.generator.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @auth guozhenhua
 * @date 2018/12/21
 */
@Getter
@Setter
public class VmFileEntity {

    //buildApi文件
    private  String build_api_vm;

    private String build_web_vm;
    // Service模板路径
    private  String service_vm;

    //实体
    private String entity_vm;

    private String entity_dto_vm;

    private String constants_vm;



    //buildServer文件
    private  String build_server_vm ;

    private String mapper_vm;

    private String mapper_provider_vm;

    // ServiceImpl模板路径
    private  String service_impl_vm;

    private String basePath;

    private String controller_vm;

    private String vo_vm;

    private String module_vm;



}
