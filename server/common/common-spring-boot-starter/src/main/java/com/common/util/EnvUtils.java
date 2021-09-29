package com.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

public class EnvUtils {

    private static final Logger logger = LoggerFactory.getLogger(EnvUtils.class);


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