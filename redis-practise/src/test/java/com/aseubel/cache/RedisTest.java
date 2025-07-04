package com.aseubel.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Aseubel
 * @date 2025/7/4 下午11:23
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void test() {
        redisTemplate.opsForValue().set("key", "value");
        String value = redisTemplate.opsForValue().get("key");
        System.out.println(value);
    }
}
