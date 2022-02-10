package com.common.middle.redis;

import com.common.util.LogUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author guozhenhua
 * @date 2020/07/11
 */
public class RedisLockUtil {

   private static RedissonClient redissonClient;

    private static final String PREX_LOAK="redis_lock:";


    public static void initRedissonClient(RedissonClient redissonClientParams) {
        redissonClient = redissonClientParams;
        LogUtil.info("redis lock init success");
    }

    public static RLock lock(String key){
        RLock lock = redissonClient.getLock(PREX_LOAK+key);
        lock.lock();
        return lock;
    }


    public static RLock lock(String lockKey, int leaseTime) {
        RLock lock = redissonClient.getLock(PREX_LOAK+lockKey);
        lock.lock(leaseTime, TimeUnit.SECONDS);
        return lock;
    }

    public static RLock lock(String lockKey, TimeUnit unit ,int timeout) {
        RLock lock = redissonClient.getLock(PREX_LOAK+lockKey);
        lock.lock(timeout, unit);
        return lock;
    }

    public static boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        RLock lock = redissonClient.getLock(PREX_LOAK+lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    public static void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(PREX_LOAK+lockKey);
        lock.unlock();
    }

    public static void unlock(RLock lock) {
        lock.unlock();
    }




}
