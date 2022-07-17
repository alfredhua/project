package com.common.redis.config;

import com.common.redis.client.RedisClient;
import com.common.util.LogUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Autowired
    RedisConnectionFactory connectionFactory;

    public void init(){
        RedisClient.initRedisTemplate(createRedisTemplate(connectionFactory));
        LogUtil.info("redis init success");
    }

    private static<T> RedisTemplate<String, T> createRedisTemplate(RedisConnectionFactory connectionFactory){
    RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setHashKeySerializer(stringRedisSerializer);
    redisTemplate.setValueSerializer(valueSerializer());
    redisTemplate.setHashValueSerializer(valueSerializer());
    redisTemplate.setEnableTransactionSupport(true);
    redisTemplate.setConnectionFactory(connectionFactory);
    redisTemplate.setDefaultSerializer(valueSerializer());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  private static RedisSerializer valueSerializer() {
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    objectMapper.activateDefaultTyping( LaissezFaireSubTypeValidator.instance ,
            ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.registerModule(new JavaTimeModule());
    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
    return jackson2JsonRedisSerializer;
  }


}
