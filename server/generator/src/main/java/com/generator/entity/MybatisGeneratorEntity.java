package com.generator.entity;

import lombok.Getter;

/**
 * @auth guozhenhua
 * @date 2018/12/06
 */
@Getter
public class MybatisGeneratorEntity {

    private String outPath;

    private String module;

    private String database;

    private String tablePrefix;

    private String packageName;


    private String apiEntityPath;

    private String apiEntityDTOPath;

    private String apiServicePath;

    private String apiConstants;


    private boolean isBuildGradle;

    private String mapperPath;

    private String mapperProviderPath;

    private String serviceImplPath;

    private String controllerPath;

    private String voPath;




    private static MybatisGeneratorEntity mybatisGeneratorEntity =null;

    public static MybatisGeneratorEntity getInstance(){
        if (mybatisGeneratorEntity ==null){
            synchronized(MybatisGeneratorEntity.class){
                if(mybatisGeneratorEntity ==null) {
                    mybatisGeneratorEntity = new MybatisGeneratorEntity();
                }
            }
        }
        return mybatisGeneratorEntity;

    }

    public MybatisGeneratorEntity setApiConstants(String apiConstants) {
        this.apiConstants = apiConstants;
        return this;
    }

    public MybatisGeneratorEntity setApiServicePath(String apiServicePath) {
        this.apiServicePath = apiServicePath;
        return this;
    }

    public MybatisGeneratorEntity setApiEntityDTOPath(String apiEntityDTOPath) {
        this.apiEntityDTOPath = apiEntityDTOPath;
        return this;
    }

    public MybatisGeneratorEntity setApiEntityPath(String apiEntityPath) {
        this.apiEntityPath = apiEntityPath;
        return this;
    }

    public MybatisGeneratorEntity setIsBuildGradle(boolean isBuildGradle) {
        this.isBuildGradle = isBuildGradle;
        return this;
    }

    public MybatisGeneratorEntity setOutPath(String outPath) {
        this.outPath = outPath;
        return this;
    }

    public MybatisGeneratorEntity setModule(String module) {
        this.module = module;return this;
    }

    public MybatisGeneratorEntity setDatabase(String database) {
        this.database = database;return this;
    }

    public MybatisGeneratorEntity setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;return this;
    }

    public MybatisGeneratorEntity setPackageName(String packageName) {
        this.packageName = packageName;return this;
    }

    public MybatisGeneratorEntity setMapperPath(String mapperPath) {
        this.mapperPath = mapperPath;return this;
    }

    public MybatisGeneratorEntity setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;return this;
    }

    public MybatisGeneratorEntity setMapperProviderPath(String mapperProviderPath) {
        this.mapperProviderPath = mapperProviderPath;return this;
    }

    public MybatisGeneratorEntity setServiceImplPath(String serviceImplPath) {
        this.serviceImplPath = serviceImplPath;return this;
    }

    public MybatisGeneratorEntity setVoPath(String voPath) {
        this.voPath = voPath;return this;
    }
}
