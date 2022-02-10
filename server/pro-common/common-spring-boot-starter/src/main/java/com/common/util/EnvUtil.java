package com.common.util;

import org.springframework.core.env.Environment;

public class EnvUtil {

    private static Environment env;

    public static void initEnv(Environment envParam){
        env=envParam;
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