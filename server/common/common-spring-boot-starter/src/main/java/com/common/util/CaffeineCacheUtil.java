package com.common.util;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * @author guozhenhua
 * @date 2021/01/24
 */
public class CaffeineCacheUtil {

    private static Cache<String, Object> cache= Caffeine.newBuilder()
            .initialCapacity(100)//初始大小
            .build();

    /**
     * 缓存是否存在，存在则不会重新加载创建cache，当参数为null时会抛空指针异常
     */
    public static Object getIfPresent(Object key) {
        return cache.getIfPresent(key);
    }


    /**
     * 刷新缓存
     */
    public static void put(String key, Object obj) {
        cache.put(key, obj);
    }

    /**
     * 清空缓存
     */
    public static void invalidate(Object key) {
        cache.invalidate(key);
    }

}
