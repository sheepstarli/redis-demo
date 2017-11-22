package com.example.redisdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * DemoConfiguration
 *
 * @author Chenxing Li
 * @date 2017/11/22 21:06
 */
@Slf4j
@Configuration
public class DemoConfiguration {

    @Value("${spring.redis.demo.host}")
    private String redisHost;
    @Value("${spring.redis.demo.port}")
    private int redisPort;
    @Value("${spring.redis.demo.password}")
    private String redisPassword;
    @Value("${spring.redis.demo.timeout}")
    private int redisTimeout;

    @Value("${spring.redis.pool.demo.max-idle}")
    private int redisPoolMaxIdle;
    @Value("${spring.redis.pool.demo.min-idle}")
    private int redisPoolMinIdle;
    @Value("${spring.redis.pool.demo.max-wait}")
    private int redisPoolMaxWait;
    @Value("${spring.redis.pool.demo.max-active}")
    private int redisPoolMaxActive;

    @Bean
    public RedisTemplate<String, String> demoRedisTemplate() {
        log.info("demoRedisTemplate init with properties");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisPoolMaxIdle);
        jedisPoolConfig.setMinIdle(redisPoolMinIdle);
        jedisPoolConfig.setMaxWaitMillis(redisPoolMaxWait);
        jedisPoolConfig.setMaxTotal(redisPoolMaxActive);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        jedisConnectionFactory.setHostName(redisHost);
        jedisConnectionFactory.setPort(redisPort);
        jedisConnectionFactory.setPassword(redisPassword);
        jedisConnectionFactory.setTimeout(redisTimeout);
        jedisConnectionFactory.afterPropertiesSet();

        RedisTemplate<String, String> stringRedisTemplate = new RedisTemplate<String, String>() {
            @Override
            protected RedisConnection preProcessConnection(RedisConnection connection,
                                                           boolean existingConnection) {
                return new DefaultStringRedisConnection(connection);
            }
        };
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        stringRedisTemplate.setKeySerializer(stringSerializer);
        stringRedisTemplate.setValueSerializer(stringSerializer);
        stringRedisTemplate.setHashKeySerializer(stringSerializer);
        stringRedisTemplate.setHashValueSerializer(stringSerializer);
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
        stringRedisTemplate.setEnableTransactionSupport(true);
        return stringRedisTemplate;
    }
    
}
