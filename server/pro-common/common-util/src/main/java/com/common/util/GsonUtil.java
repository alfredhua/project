package com.common.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class GsonUtil {

    public static final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    /**
     * map转String
     * @param map
     * @return
     */
    public static String toJSONString(Map map){
        return gson.toJson(map);
    }


    public static Map<String,Object> objectToMap(Object o){
        return gson.fromJson(toJSON(o),new TypeToken<Map<String, Object>>() {}.getType());
    }

    /**
     * Object转String
     * @param m
     * @return
     */
    public static String toJSON(Object m){
        return gson.toJson(m);
    }

    /**
     * json 转 map
     * @param str
     * @return
     */
    public static Map<String,Object> jsonStringToMap(String str){
        return gson.fromJson(str,new TypeToken<Map<String, Object>>() {}.getType());
    }

    /**
     * Json 转Object
     * @param json
     * @return
     */
    public static Object toObject(String json){
        return gson.fromJson(json, new TypeToken<Object>(){}.getType());
    }


    public static <T> T toClass(Object object,Class<T> clazz){
        String paramsStr=gson.toJson(object);
        return gson.fromJson(paramsStr,clazz);
    }

}
