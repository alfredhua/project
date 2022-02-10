package com.common.util;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.env.Environment;

public class EnvUtil {

    private static Environment env;

    public static void initEnv(Environment envParam){
        env=envParam;
    }

    public static Environment getEnvironment(){
        if (ObjectUtils.isEmpty(env)){
            throw new RuntimeException("environment 配置错误!");
        }
        return env;
    }

    public static boolean isDevActive(){
        String[] activeProfiles = env.getActiveProfiles();
        for (String profile: activeProfiles) {
            if ("dev".equalsIgnoreCase(profile)){
                return true;
            }
        }
        return false;
    }
}