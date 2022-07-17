package com.common.util;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.env.Environment;

/**
 * 当前环境获取
 * @author hua
 */
@Getter
public class EnvUtil {

    private static final String DEFAULT_ENV="dev";

    private static Environment env;

    public static void initEnv(Environment envParam){
        if (ObjectUtils.isEmpty(env)){
            env=envParam;
        }
    }

    public static Environment getEnvironment(){
        if (ObjectUtils.isEmpty(env)){
            throw new RuntimeException("environment 配置错误!");
        }
        return env;
    }

    public static String getActiveProfile(){
        String[] activeProfiles = env.getActiveProfiles();
        for (String profile: activeProfiles) {
            return profile;
        }
        return DEFAULT_ENV;
    }

    public static boolean isDevActive(){
        String[] activeProfiles = env.getActiveProfiles();
        for (String profile: activeProfiles) {
            if (DEFAULT_ENV.equalsIgnoreCase(profile)){
                return true;
            }
        }
        return false;
    }
}