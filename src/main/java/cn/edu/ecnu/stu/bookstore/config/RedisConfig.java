package cn.edu.ecnu.stu.bookstore.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig implements InitializingBean {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void afterPropertiesSet(){
        RedisSerializer stringSerializer = new StringRedisSerializer();
        //key序列化方式
        redisTemplate.setKeySerializer(stringSerializer);
        //String的序列化方式
        redisTemplate.setStringSerializer(stringSerializer);
        //value序列化方式
        redisTemplate.setValueSerializer(stringSerializer);
        //hash key序列化方式
        redisTemplate.setHashKeySerializer(stringSerializer);
        //hash value序列化方式
        redisTemplate.setHashValueSerializer(stringSerializer);
    }
}
