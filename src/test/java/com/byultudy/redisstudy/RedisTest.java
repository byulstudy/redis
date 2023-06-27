package com.byultudy.redisstudy;

import com.byultudy.redisstudy.concert.ConcertDto;
import com.byultudy.redisstudy.customer.Customer;
import com.byultudy.redisstudy.redis.RedisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisTest {

    RedisRepository redisRepository;

    RedisTemplate<String, String> redisTemplate;

    @BeforeEach
    void setUp() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("localhost");
        redisStandaloneConfiguration.setPort(6379);
        LettuceConnectionFactory redisConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        redisConnectionFactory.afterPropertiesSet();

        redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();

//        redisRepository = new RedisRepository(redisTemplate);
    }

    int rsvCount = 0;

    @Test
    void redisTest() {
        ConcertDto concertDto = ConcertDto.builder()
                .id(1L)
                .ticketQuantity(100L)
                .build();

        Object concert = redisTemplate.opsForHash().get("concert", "1");
        System.out.println(concert);
    }

}