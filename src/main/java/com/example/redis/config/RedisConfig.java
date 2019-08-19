package com.example.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${redis.hostname}")
    private String hostname;

    @Value("${redis.port}")
    private Integer port;

    public static final Long TWELVE_HOURS_CACHE_EXPIRES = TimeUnit.HOURS.toSeconds(12);
    public static final Long TWELVE_MINS_CACHE_EXPIRES = TimeUnit.MINUTES.toSeconds(2);
    public static final String USER_NAME_CACHE = "userNames";
    public static final String USER_NAME_KEY = "#username";

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(hostname);
        connectionFactory.setPort(port);
        return connectionFactory;
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate() {

        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

/*    @Bean
    StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionFactory());
        stringRedisTemplate.setEnableTransactionSupport(true);
        return stringRedisTemplate;
    }*/

    @Bean
    public CacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory) {

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        cacheConfigurations.put(USER_NAME_CACHE, createCacheConfiguration(TWELVE_MINS_CACHE_EXPIRES));

        return RedisCacheManager.builder(jedisConnectionFactory)
                .cacheDefaults(createCacheConfiguration(TWELVE_HOURS_CACHE_EXPIRES))
                .withInitialCacheConfigurations(cacheConfigurations)
                .transactionAware()
                .build();
    }

    private static RedisCacheConfiguration createCacheConfiguration(long timeoutInSeconds) {
        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(timeoutInSeconds));
    }
}
