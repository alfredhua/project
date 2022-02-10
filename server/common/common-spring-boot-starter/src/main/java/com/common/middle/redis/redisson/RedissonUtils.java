package com.common.middle.redis.redisson;

import com.common.util.EnvUtil;
import com.common.util.LoadPropertiesUtil;
import com.common.util.LogUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class RedissonUtils {

    private static final long DEFAULT_TIME_OUT=30*24*60;

    private static final String CONFIG_FILE="redisson.config.file";

    public static RedissonClient redissonClient;

    static {
        try {
            String filePath = EnvUtil.getEnvironment().getProperty(CONFIG_FILE);
            if (!ObjectUtils.isEmpty(filePath)) {
                URL url = RedissonUtils.class.getClassLoader().getResource(filePath);
                InputStream input = new FileInputStream(url.getPath());
                Config config= Config.fromYAML(input);
                redissonClient = Redisson.create(config);
            }
        }catch (Exception e){
            LogUtil.error("redisson init error",e);
        }
    }

    public static void setString(String key, Object value,Long time) {
        RBucket<Object> result = redissonClient.getBucket(key);
        if (!result.isExists()) {
            result.set(value, 5, TimeUnit.MINUTES);
        }
    }

    public static boolean hasString(String key) {
        return redissonClient.getBucket(key).isExists();
    }

}
