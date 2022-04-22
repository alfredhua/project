package com.common.redis.client;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisClient {

  private static final long DEFAULT_TIME_OUT=30*24*60;

  private static RedisTemplate<String,Object> template;

  public static void initRedisTemplate( RedisTemplate<String, Object> redisTemplate) {
     template= redisTemplate;
  }

  private static RedisTemplate<String,Object> getTemplate(){
    if (ObjectUtils.isEmpty(template)){
      throw new RuntimeException("redis connection error");
    }
    return template;
  }

  /**
   * 设置过期时间，默认单位秒
   * @param key key
   * @param timeout 过期时间
   */
  public static void expire(String key,long timeout){
    if (timeout==0) {
      getTemplate().expire(key, DEFAULT_TIME_OUT, TimeUnit.SECONDS);
    }else{
      getTemplate().expire(key, timeout, TimeUnit.SECONDS);
    }
  }

  /**
   * 从redis中随机返回一个key
   * @return
   */
  public static Object randomKey() {
    return getTemplate().randomKey();
  }

  /**
   * 返回 key 所储存的值的类型
   * @param key
   * @return
   */
  public static DataType getType(String key) {
    return getTemplate().type(key);
  }


  /**
   * 根据key 获取过期时间
   * @param key 键 不能为null
   * @return 时间(秒) 返回0代表为永久有效
   */
  public static Long getExpire(String key) {
    return getTemplate().getExpire(key, TimeUnit.SECONDS);
  }

  /**
   * 验证key是否存在
   * @param key key
   */
  public static Boolean hasKey(String key) {
    return getTemplate().hasKey(key);
  }

  /**
   * 删除
   * @param key key
   */
  public static void del(String... key) {
    if (key != null && key.length > 0) {
      if (key.length == 1) {
        getTemplate().delete(key[0]);
      } else {
        getTemplate().delete((Collection<String>) CollectionUtils.arrayToList(key));
      }
    }
  }

  /**
   * Object类型存储
   * @param key  key
   * @param timeout 过期时间，0：表示使用默认30天,null：表示永不过期
   * @param value
   */
  public static <T> void objectSet(String key,Long timeout,T value){
    try {
      if (timeout==null){
        getTemplate().opsForValue().set(key, value);
      }else if (timeout==0) {
        getTemplate().opsForValue().set(key,value, DEFAULT_TIME_OUT, TimeUnit.SECONDS);
      }else{
        getTemplate().opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
      }
    }catch (Exception e){
      throw new RuntimeException("set error",e);
    }
  }
  /**
   * Object 类型获取
   * @param key
   * @return
   */
  public static <T>  T objectGet(String key){
    try {
      return (T) getTemplate().opsForValue().get(key);
    }catch ( Exception e){
      throw new RuntimeException("redis get error",e);
    }
  }

  /**
   * 递增
   * @param key  key
   * @param delta 递增因子，0或者null时候：默认是1,负数表示递减。
   * @return
   */
  public static Long increment(String key, Long delta) {
    if(delta==null||delta==0){
      return getTemplate().opsForValue().increment(key, 1L);
    }
    return getTemplate().opsForValue().increment(key, delta);
  }


  /**
   * HashGet
   *
   * @param key  键 不能为null
   * @param field 项 不能为null
   * @return 值
   */
  public static Object hashGet(String key, String field) {
    return getTemplate().opsForHash().get(key, field);
  }

  /**
   * 向一张hash表中放入数据,如果不存在将创建
   * @param key   键
   * @param field  项
   * @param value 值
   */
  public static void hashSet(String key, String field, Object value) {
    getTemplate().opsForHash().put(key, field, value);
  }

  /**
   *
   * @param key key
   * @param map 对象
   */
  public static void hashPutAll(String key, Map<Object, Object> map){
    getTemplate().opsForHash().putAll(key,map);
  }

  /**
   * 获取map
   * @param key
   * @return
   */
  public static Map<Object, Object> hashGetAll(String key){
    return getTemplate().opsForHash().entries(key);
  }

  /**
   * 返回这个key里面所有fields的值
   * @param key
   * @param fields
   * @return
   */
  public static List<Object> multiHashGet(String key, String...fields) {
    return getTemplate().opsForHash().multiGet(key, (Collection<Object>)  CollectionUtils.arrayToList(fields));
  }



  /**
   * 判断key中的hashKey是否存在
   * @param key
   * @param field
   */
  public Boolean hasKey(String key,String field) {
    return getTemplate().opsForHash().hasKey(key, field);
  }

  /**
   * list left push
   * @param key
   * @param value
   */
  public void listLeftPush(String key,Object value) {
    getTemplate().opsForList().leftPush(key, value);
  }

  /**
   * list left pop
   * @param key
   * @return
   */
  public Object listLeftPop(String key) {
    return getTemplate().opsForList().leftPop(key);
  }



  /**
   * list right push
   * @param key
   * @param value
   */
  public void listRightPush(String key,Object value) {
    getTemplate().opsForList().rightPush(key, value);
  }

  /**
   * list right pop
   * @param key
   * @return
   */
  public Object listRightPop(String key) {
    return getTemplate().opsForList().rightPop(key);
  }


  /**
   * 获取该key index处的元素
   * @param key
   * @param index
   * @return
   */
  public Object listGetIndex(String key,int index) {
    return getTemplate().opsForList().index(key, index);
  }

  /**
   * 获取列表的长度
   * @param key
   * @return
   */
  public Long listSize(String key) {
    return getTemplate().opsForList().size(key);
  }


  /**
   * 获取key中下标从start到end处的值
   * @param key
   * @param start 开始下标
   * @param end  结束下标
   * @return
   */
  public List<Object> listRange(String key,int start,int end){
    return getTemplate().opsForList().range(key, start, end);
  }

  /**
   * 删除
   * @param key
   * @return
   */
  public Long listRemove(String key,long count,Object value){
    return getTemplate().opsForList().remove(key, count, value);
  }

  /**
   * 向set 集合中添加
   * @param key
   * @param values
   */
  public void setAdd(String key,Object...values) {
    getTemplate().opsForSet().add(key, values);
  }

  /**
   * 移除并取出第一个元素
   * @param key
   * @return
   */
  public Object setGet(String key){
    return getTemplate().opsForSet().pop(key);
  }

  /**
   * 返回集合中所有的元素
   * @param key
   * @return
   */
  public Set<Object> setGetAll(String key){
    return getTemplate().opsForSet().members(key);
  }


  /**
   * 返回指定数量的元素(随机)
   * @param key
   * @param count
   * @return
   */
  public List<Object> setRandomMembers(String key,int count){
    return getTemplate().opsForSet().randomMembers(key, count);
  }

  /**
   * 返回集合中的长度
   * @param key
   * @return
   */
  public Long setGetSize(String key){
    return getTemplate().opsForSet().size(key);
  }

  /**
   * 返回给定集合的差集（返回 key在otherKeys不存在的元素）
   * @param key 主集合
   * @param otherKeys 其他集合
   * @return
   */
  public Set<Object> setDifference(String key,String...otherKeys){
    return getTemplate().opsForSet().difference(key,(Collection<String>)   CollectionUtils.arrayToList(otherKeys));
  }

  /**
   * 返回给定集合的交集（返回 key与otherKeys中共同存在的元素）
   * @param key
   * @param otherKeys
   * @return
   */
  public Set<Object> setIntersect(String key,String...otherKeys){
    return getTemplate().opsForSet().intersect(key, (Collection<String>) CollectionUtils.arrayToList(otherKeys));
  }
  /**
   * 返回给定集合的并集（key和otherKeys加起来的所有元素，共同拥有的元素只返回一个）
   * @param key
   * @param otherKeys
   * @return
   */
  public Set<Object> setUnion(String key,String...otherKeys){
    return getTemplate().opsForSet().union(key, (Collection<String>) CollectionUtils.arrayToList(otherKeys));
  }

  /**
   * 返回集合中的所有元素
   * @param key
   * @return
   */
  public Set<Object> setMembers(String key){
    return getTemplate().opsForSet().members(key);
  }

  /**
   * 迭代集合中的元素
   * @param key
   * @return
   */
  public Cursor<Object> setScan(String key){
    return getTemplate().opsForSet().scan(key, ScanOptions.NONE);
  }

  //zSet操作 有序集合

  /**
   * 添加数据
   *
   * 添加方式：
   * 1.创建一个set集合
   * Set<ZSetOperations.TypedTuple<Object>> sets=new HashSet<>();
   * @param key
   * @param tuples
   */
  public void zSetAdd(String key,Set<ZSetOperations.TypedTuple<Object>> tuples) {
    getTemplate().opsForZSet().add(key, tuples);
  }

  /**
   * 获取有序集合的成员数
   * @param key
   * @return
   */
  public Long zSetCard(String key) {
    return getTemplate().opsForZSet().zCard(key);
  }

  /**
   * 计算在有序集合中指定区间分数的成员数
   * @param key
   * @param min 最小排序分数
   * @param max 最大排序分数
   * @return
   */
  public Long zSetCount(String key,Double min,Double max) {
    return getTemplate().opsForZSet().count(key, min, max);
  }

  /**
   * 获取有序集合下标区间 start 至 end 的成员  分数值从小到大排列
   * @param key
   * @param start
   * @param end
   */
  public Set<Object> zSetRange(String key,int start,int end) {
    return getTemplate().opsForZSet().range(key, start, end);
  }

  /**
   * 获取有序集合下标区间 start 至 end 的成员  分数值从大到小排列
   * @param key
   * @param start
   * @param end
   */
  public Set<Object> zSetReverseRange(String key,int start,int end) {
    return getTemplate().opsForZSet().reverseRange(key, start, end);
  }

  /**
   * 返回 分数在min至max之间的数据 按分数值递减(从大到小)的次序排列。
   * @param key
   * @param min
   * @param max
   * @return
   */
  public Set<Object> zSetReverseRange(String key,Double min,Double max) {
    return getTemplate().opsForZSet().reverseRangeByScore(key, min, max);
  }

  /**
   * 返回指定成员的下标
   * @param key
   * @param value
   * @return
   */
  public Long zSetRank(String key,Object value) {
    return getTemplate().opsForZSet().rank(key, value);
  }

  /**
   * 删除key的指定元素
   * @param key
   * @param values
   * @return
   */
  public Long zSetRemoveValue(String key,Object values) {
    return getTemplate().opsForZSet().remove( key, values);
  }

  /**
   * 移除下标从start至end的元素
   * @param key
   * @param start
   * @param end
   * @return
   */
  public Long zSetRemoveRange(String key,int start,int end) {
    return getTemplate().opsForZSet().removeRange(key, start, end);
  }

  /**
   * 移除分数从min至max的元素
   * @param key
   * @param min
   * @param max
   * @return
   */
  public Long zSetRemoveRangeByScore(String key,Double min,Double max) {
    return getTemplate().opsForZSet().removeRangeByScore(key, min, max);
  }




}
