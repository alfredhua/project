package com.common.middle.redis.jedis;

import com.common.util.GsonUtil;
import com.common.util.LoadPropertiesUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.params.SetParams;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class RedisUtil {

    private static JedisPool jedisPool;

    private static final String CONFIG_FILE="redis.config.file";

    private static final int MAX_WAIT = 15 * 1000;

    private static final int TIMEOUT = 10 * 1000;

    private static final Properties properties;

    static {
        properties= LoadPropertiesUtil.loadConfig(CONFIG_FILE);
        if (!ObjectUtils.isEmpty(properties)) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(properties.getProperty("redis.maxTotal", "400")));
            config.setMaxIdle(Integer.parseInt(properties.getProperty("redis.maxIdle", "50")));
            config.setMinIdle(Integer.parseInt(properties.getProperty("redis.minIdle", "50")));
            config.setTestOnBorrow(Boolean.parseBoolean(properties.getProperty("redis.testOnBorrow", "false")));
            config.setTestOnReturn(Boolean.parseBoolean(properties.getProperty("redis.testOnReturn", "false")));
            config.setTestWhileIdle(Boolean.parseBoolean(properties.getProperty("redis.TestWhileIdle", "true")));
            config.setTestOnCreate(Boolean.parseBoolean(properties.getProperty("redis.testOnCreate", "true")));
            config.setNumTestsPerEvictionRun(1000);
            config.setMaxWaitMillis(MAX_WAIT);
            String host = properties.getProperty("redis.host", "localhost");
            String port = properties.getProperty("redis.port", "6379");
            String password = properties.getProperty("redis.password", "");
            if (StringUtils.isNotBlank(password)) {
                jedisPool = new JedisPool(config, host, Integer.parseInt(port), TIMEOUT, password);
            } else {
                jedisPool = new JedisPool(config, host, Integer.parseInt(port), TIMEOUT);
            }
        }
    }

    public static String getRedisProperty(String property){
        return properties.getProperty(property);
    }

    /**
     * 获取jedis
     *
     * @return
     */
    private static Jedis getJedis() {
        if (ObjectUtils.isEmpty(jedisPool)){
            throw new RuntimeException("jedis加载错误，请配置jedis配置文件");
        }
        return jedisPool.getResource();
    }

    public static void objectSet(String key,long time,Object value){
        set(key, GsonUtil.toJSON(value),time);
    }

    public static Object objectGet(String key){
        return get(key);
    }
    
    /**
     * get
     * @param key
     * @return
     */
    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public static void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } catch (Exception e) {
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }
    /**
     * set with expire milliseconds
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public static void set(String key, String value, long seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            SetParams params=new SetParams();
            params.ex(seconds);
            params.nx();
            params.px(seconds);
            jedis.set(key, value,params);
        } catch (Exception e) {
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }

    public static Long incr(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.incr(key);
        }catch (Exception e){
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static void hset(String key,String field,String value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hset(key,field,value);
        }catch (Exception e){
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static String hget(String key,String field){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.hget(key,field);
        }catch (Exception e){
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static Map<String,String> hgetAll(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.hgetAll(key);
        }catch (Exception e){
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    /**
     *
     * @param timeout 0表示永久 单位秒
     * @param key key
     * @return [key,value]
     */
    public static String blpop(int timeout,String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            List<String> list = jedis.blpop(timeout, key);
            return list.get(1);
        }catch (Exception e){
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static String blpop(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            List<String> list = jedis.blpop(0, key);
            return list.get(1);
        }catch (Exception e){
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static void lpush(String key,String... value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.lpush(key,value);
        }catch (Exception e){
            throw new JedisException(e.getMessage(),e);
        }
    }

    /**
     *
     * @param timeout 0表示永久 单位秒
     * @param key key
     * @return [key,value]
     */
    public static String brpop(int timeout,String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            List<String> list = jedis.brpop(timeout, key);
            return list.get(1);
        }catch (Exception e){
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static String brpop(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            List<String> list = jedis.brpop(0, key);
            return list.get(1);
        }catch (Exception e){
            throw new JedisException(e.getMessage(),e);
        }finally {
            close(jedis);
        }
    }

    public static void rpush(String key,String... value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.rpush(key,value);
        }catch (Exception e){
            throw new JedisException(e.getMessage(),e);
        }
    }

    /**
     * 获取key过期时间 -1表示永久 -2表示该key不存在
     * @param key
     * @return
     */
    public static long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.ttl(key);
        } catch (Exception e) {
            throw new JedisException(e.getMessage(),e);
        } finally {
            close(jedis);
        }
    }

    private static void close(Jedis jedis){
        if (ObjectUtils.isEmpty(jedis)) return;
        jedis.close();
    }

}
