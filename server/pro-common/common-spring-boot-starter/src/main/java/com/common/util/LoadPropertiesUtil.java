package com.common.util;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class LoadPropertiesUtil {

    private static final String YML="yml";

    private static final String PROPERTIES="properties";

     public static Properties loadConfig(String configPath){
         String filePath = EnvUtil.getEnvironment().getProperty(configPath);
         if (ObjectUtils.isEmpty(filePath)){
             return null;
         }
         return load(filePath);
     }

    public static Properties load(String fileName){
        if (StringUtils.isEmpty(fileName)){
            throw new RuntimeException("file name is null");
        }
        String fileType =  fileName.substring(fileName.lastIndexOf(".") + 1);
        if (PROPERTIES.equals(fileType)){
            return loadByProperties(fileName);
        }
        if (YML.equals(fileType)){
            return loadByYml(fileName);
        }
        URL url = LoadPropertiesUtil.class.getClassLoader().getResource(fileName + ".properties");
        if (ObjectUtils.isEmpty(url)){
            url = LoadPropertiesUtil.class.getClassLoader().getResource(fileName + ".yml");
        }
        return loadByPropertiesUrl(url);
    }


    public static Properties loadByYml(String fileName) {
        URL url = LoadPropertiesUtil.class.getClassLoader().getResource(fileName );
        if (ObjectUtils.isEmpty(url)){
            throw new RuntimeException("load yml file is not exists");
        }
        return loadByYmlURL(url);
    }

    public static Properties loadByProperties(String fileName) {
        URL url = LoadPropertiesUtil.class.getClassLoader().getResource(fileName);
        if (ObjectUtils.isEmpty(url)){
            throw new RuntimeException("load properties file is not exists");
        }
        return loadByPropertiesUrl(url);
    }


    //todo 待实现
    private static Properties loadByYmlURL(URL url){
        try {
            Yaml yaml = new Yaml();
            yaml.loadAs(new FileReader(url.getPath()), HashMap.class);
            return new Properties();
        }catch (Exception e){
            throw new RuntimeException("load yml file error",e);
        }
    }

    private static Properties loadByPropertiesUrl(URL url){
        try {
            Properties properties=new Properties();
            InputStream input = new FileInputStream(url.getPath());
            properties.load(input);
            return properties;
        }catch (Exception e){
            throw new RuntimeException("load properties file error",e);
        }
    }

}
