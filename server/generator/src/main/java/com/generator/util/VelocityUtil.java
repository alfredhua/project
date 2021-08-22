package com.generator.util;


import java.io.File;

/**
 * @auth guozhenhua
 * @date 2018/12/06
 */
public class VelocityUtil {


//    public static void apiBuild(String buildApi_vm)throws Exception{
//        VelocityContext context1 = new VelocityContext();
//        context1.put("module", MODULE);
//        String buildApi = outPath+ MODULE+"/"+MODULE+"-api/build.gradle";
//        generate(buildApi_vm, buildApi, context1);
//    }
//
//    public static void webBuild(String buildApi_vm)throws Exception{
//        VelocityContext context1 = new VelocityContext();
//        context1.put("module", MODULE);
//        String buildApi = outPath+ MODULE+"/"+MODULE+"-web/build.gradle";
//        generate(buildApi_vm, buildApi, context1);
//    }
//
//
//    public static void apiEntity(List<ColumnEntity> list,String entityName, String entity_vm)throws Exception{
//        VelocityContext context = new VelocityContext();
//        context.put("packageName", PACKAGE_NAME);
//        context.put("entityName", entityName);
//        context.put("entity", "entity");
//        context.put("columnEntityList", list);
//        context.put("time",DateTimeUtil.getDate());
//        File file=new File(API_ENTITY);
//        if (!file.exists()){
//            file.mkdirs();
//        }
//        String entity =API_ENTITY+entityName+".java";
//        generate(entity_vm, entity, context);
//    }
//
//
//    public static void serverVo(String entityName,String vo_vm)throws Exception{
//        String name=entityName+"CreateReqVO";
//        VelocityContext context = new VelocityContext();
//        context.put("packageName", PACKAGE_NAME);
//        context.put("entityName", entityName);
//        context.put("loseEntityName", entityName.toLowerCase());
//        context.put("entityNameVO", name);
//        context.put("module",MODULE);
//        context.put("time",DateTimeUtil.getDate());
//        File file=new File(VO+entityName.toLowerCase());
//        if (!file.exists()){
//            file.mkdirs();
//        }
//        String entity =VO+entityName.toLowerCase()+"/"+name+".java";
//        generate(vo_vm, entity, context);
//
//        //respVO
//        String entityName4=entityName+"UpdateReqVO";
//        String entity4=VO+entityName.toLowerCase()+"/"+entityName4+".java";
//        context.put("entityNameVO",entityName4 );
//        generate(vo_vm, entity4, context);
//
//
//        //respVO
//        String entityName2=entityName+"RespVO";
//        String entity2=VO+entityName.toLowerCase()+"/"+entityName2+".java";
//        context.put("entityNameVO",entityName2 );
//        generate(vo_vm, entity2, context);
//
//        //reqListVO
//        String entityName3=entityName+"ListReqVO";
//        String entity3=VO+entityName.toLowerCase()+"/"+entityName3+".java";
//        context.put("entityNameVO",entityName3 );
//        context.put("isListReq",true );
//        generate(vo_vm, entity3, context);
//
//    }
//
//    public static void apiEntityDTO(String entityName, String entity_dto_vm)throws Exception{
//        //reqDTO
//        String name=entityName+"ReqDTO";
//        VelocityContext context = new VelocityContext();
//        context.put("packageName", PACKAGE_NAME);
//        context.put("entityName", entityName);
//        context.put("loseEntityName", entityName.toLowerCase());
//        context.put("entityNameDTO", name);
//        context.put("type","dto" );
//        context.put("time",DateTimeUtil.getDate());
//        File file=new File(API_ENTITY_DTO+entityName.toLowerCase());
//        if (!file.exists()){
//            file.mkdirs();
//        }
//        String entity =API_ENTITY_DTO+entityName.toLowerCase()+"/"+name+".java";
//        generate(entity_dto_vm, entity, context);
//
//        //respDTO
//        String entityName2=entityName+"RespDTO";
//        String entity2=API_ENTITY_DTO+entityName.toLowerCase()+"/"+entityName2+".java";
//        context.put("entityNameDTO",entityName2 );
//        generate(entity_dto_vm, entity2, context);
//
//        //reqList
//        String entityName3=entityName+"ListReqDTO";
//        String entity3=API_ENTITY_DTO+entityName.toLowerCase()+"/"+entityName3+".java";
//        context.put("entityNameDTO",entityName3 );
//        context.put("isListReq",true );
//        generate(entity_dto_vm, entity3, context);
//    }
//
//
//  /
//
//    public static void apiConstants( String entityName, String constants_vm)throws Exception {
//        VelocityContext context = new VelocityContext();
//        context.put("packageName", PACKAGE_NAME);
//        context.put("entityName", entityName);
//        context.put("lowerEntityName", entityName.toLowerCase());
//        context.put("ctime",DateTimeUtil.getDate());
//        String entity =CONSTANTS+entityName.toLowerCase()+"/"+entityName+"ErrorEnum.java";
//        File file=new File(CONSTANTS+entityName.toLowerCase());
//        if (!file.exists()){
//            file.mkdirs();
//        }
//        generate(constants_vm, entity, context);
//    }
//
//
//
//
//    public static void webModuleUrl(List<ModuleEntity> list, String buildServer_vm)throws Exception{
//        VelocityContext context = new VelocityContext();
//
//        context.put("packageName",PACKAGE_NAME);
//        context.put("ctime",DateTimeUtil.getDate());
//        context.put("module",StringUtil.toUpperCaseFirstOne(MODULE));
//        context.put("lowerModule",MODULE.toLowerCase());
//        context.put("entityList", list);
//
//        String entity =CONTROLLER+"/"+StringUtil.toUpperCaseFirstOne(MODULE)+"Url.java";
//        File file=new File(CONTROLLER);
//        if (!file.exists()){
//            file.mkdirs();
//        }
//        generate(buildServer_vm, entity, context);
//    }
//
//
//
//    public static void serverBuild(String buildServer_vm)throws Exception{
//        VelocityContext context1 = new VelocityContext();
//        context1.put("module", MODULE);
//        String path=outPath+MODULE+"/"+MODULE+"-server";
//        File file=new File(path);
//        if (!file.exists()){
//            file.mkdirs();
//        }
//        String buildApi =path+ "/build.gradle";
//        generate(buildServer_vm, buildApi, context1);
//    }


    // 递归删除非空文件夹
    public static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteDir(files[i]);
            }
        }
        dir.delete();
    }





}
