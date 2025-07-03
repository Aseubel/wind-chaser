package com.aseubel.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @author Aseubel
 * @date 2025/7/3 下午10:01
 */
@SpringBootTest
public class CaffeineTest {
    @Test
    public void simpleTest() {
        Cache<String, String> cache = Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        // 获取并打印统计信息
        CacheStats stats = cache.stats();
        System.out.println("命中率：" + stats.hitRate());
    }
}
