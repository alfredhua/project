package com.generator.util;

import com.generator.entity.MybatisGeneratorEntity;
import com.generator.entity.VmFileEntity;
import java.io.File;

/**
 * @auth guozhenhua
 * @date 2018/12/21
 */
public class VmPathUtil {

    //buildApi文件
    private static String build_api_vm = "/template/api/build_api.vm";

    private static String build_web_vm = "/template/api/build_web.vm";

    private static String entity_vm= "/template/api/entity.vm";

    private static String entity_dto_vm= "/template/api/dto.vm";
    // Service模板路径
    private static String service_vm = "/template/api/service.vm";

    private static String constants_vm= "/template/api/constants.vm";

    //buildServer文件
    private static String build_server_vm = "/template/api/server_build.vm";

    //mapperVm文件
    private static String mapper_vm = "/template/api/mapper.vm";

    // ServiceImpl模板路径
    private static String mapper_provider_vm = "/template/api/mapper_provider.vm";

    // ServiceImpl模板路径
    private static String service_impl_vm = "/template/api/service_impl.vm";

    private static String controller_vm="/template/api/controller.vm";

    private static String vo_vm="/template/api/vo.vm";

    private static String module_vm="/template/api/module_url.vm";


    public static VmFileEntity getVm( ){
        VmFileEntity vmFileEntity =new VmFileEntity();
        String os = System.getProperty("os.name");
        String targetServerProject = PropertiesUtil.MODULE + "/" + PropertiesUtil.MODULE + "-server";
        String targetApiProject = PropertiesUtil.MODULE+ "/" +PropertiesUtil.MODULE + "-api";

        File targetServerProjectFile = new File(PropertiesUtil.outPath + targetServerProject + "/src/main/java/");
        if (!targetServerProjectFile.exists()){
            targetServerProjectFile.mkdirs();
        }

        File targetApiProjectFile = new File(PropertiesUtil.outPath+ targetApiProject + "/src/main/java/");
        if (!targetApiProjectFile.exists()){
            targetApiProjectFile.mkdirs();
        }

        String basePath = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "").replace(targetServerProject, "");
        if (os.toLowerCase().startsWith("win")) {
            //windows

            //TODO Api模块下
            build_api_vm = MybatisGeneratorUtil.class.getResource(build_api_vm).getPath().replaceFirst("/", "");
            entity_vm= MybatisGeneratorUtil.class.getResource(entity_vm).getPath().replaceFirst("/", "");
            service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath().replaceFirst("/", "");
            entity_dto_vm = MybatisGeneratorUtil.class.getResource(entity_dto_vm).getPath().replaceFirst("/", "");
            constants_vm = MybatisGeneratorUtil.class.getResource(constants_vm).getPath().replaceFirst("/", "");

            //TODO server模块下
            build_server_vm = MybatisGeneratorUtil.class.getResource(build_server_vm).getPath().replaceFirst("/", "");
            mapper_vm = MybatisGeneratorUtil.class.getResource(mapper_vm).getPath().replaceFirst("/", "");
            mapper_provider_vm= MybatisGeneratorUtil.class.getResource(mapper_provider_vm).getPath().replaceFirst("/", "");

            service_impl_vm = MybatisGeneratorUtil.class.getResource(service_impl_vm).getPath().replaceFirst("/", "");
            controller_vm = MybatisGeneratorUtil.class.getResource(controller_vm).getPath().replaceFirst("/", "");

            vo_vm = MybatisGeneratorUtil.class.getResource(vo_vm).getPath().replaceFirst("/", "");

            module_vm = MybatisGeneratorUtil.class.getResource(module_vm).getPath().replaceFirst("/", "");

            basePath = basePath.replaceFirst("/", "");
        } else {
            //mac
            //TODO api模块下
            build_api_vm = MybatisGeneratorUtil.class.getResource(build_api_vm).getPath();
            entity_vm = MybatisGeneratorUtil.class.getResource(entity_vm).getPath();
            service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath();
            entity_dto_vm= MybatisGeneratorUtil.class.getResource(entity_dto_vm).getPath();
            constants_vm= MybatisGeneratorUtil.class.getResource(constants_vm).getPath();

            //TODO server模块下
            build_server_vm = MybatisGeneratorUtil.class.getResource(build_server_vm).getPath();
            mapper_vm = MybatisGeneratorUtil.class.getResource(mapper_vm).getPath();
            mapper_provider_vm = MybatisGeneratorUtil.class.getResource(mapper_provider_vm).getPath();
            controller_vm = MybatisGeneratorUtil.class.getResource(controller_vm).getPath();
            service_impl_vm = MybatisGeneratorUtil.class.getResource(service_impl_vm).getPath();
            vo_vm = MybatisGeneratorUtil.class.getResource(vo_vm).getPath();

            module_vm = MybatisGeneratorUtil.class.getResource(module_vm).getPath();
        }

        vmFileEntity.setBuild_api_vm(build_api_vm);
        vmFileEntity.setEntity_vm(entity_vm);
        vmFileEntity.setEntity_dto_vm(entity_dto_vm);
        vmFileEntity.setService_vm(service_vm);
        vmFileEntity.setConstants_vm(constants_vm);
        vmFileEntity.setBuild_server_vm(build_server_vm);
        vmFileEntity.setMapper_vm(mapper_vm);
        vmFileEntity.setMapper_provider_vm(mapper_provider_vm);
        vmFileEntity.setService_impl_vm(service_impl_vm);
        vmFileEntity.setController_vm(controller_vm);
        vmFileEntity.setBasePath(basePath);
        vmFileEntity.setVo_vm(vo_vm);
        vmFileEntity.setBuild_web_vm(build_web_vm);
        vmFileEntity.setModule_vm(module_vm);
        return vmFileEntity;
    }
}
