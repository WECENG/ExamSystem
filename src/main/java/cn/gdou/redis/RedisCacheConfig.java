package cn.gdou.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * redis cluster 配置
 */
@Configuration
@EnableCaching
@ComponentScan
public class RedisCacheConfig {

    @Autowired
    ClusterConfigurationProperties properties;


    //redis cluster factory
    @Bean
    public JedisConnectionFactory redisConnectionFactory(JedisPoolConfig poolConfig){
        JedisConnectionFactory jedisConnectionFactory=
                new JedisConnectionFactory(
                        new RedisClusterConfiguration(properties.getNodes()),
                        poolConfig);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    //有了该bean就能使用Repository
    @Bean
    public RedisTemplate<?,?> redisTemplate(
            RedisConnectionFactory redisConnectionFactory
    ){
        RedisTemplate<?,?> redisTemplate=
                new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return redisTemplate;
    }

    //启动缓存管理
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory rcf){
        Set<String> caches=new HashSet<>();
        caches.add("StudentCaches");
        return  RedisCacheManager.builder(rcf).initialCacheNames(caches).build();
    }
}
