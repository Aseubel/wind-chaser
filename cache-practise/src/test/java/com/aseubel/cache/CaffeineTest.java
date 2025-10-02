package com.aseubel.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.junit.jupiter.api.Assertions;
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

    @Test
    public void expireTest() {
        // 创建一个缓存实例
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(100, TimeUnit.MILLISECONDS)
                .maximumSize(100)
                .build();

        // 往缓存里放一些数据
        cache.put("关键词1", "值1");
        cache.put("关键词2", "值2");

        // 从缓存中取数据
        String value1 = cache.getIfPresent("关键词1");
        System.out.println("获取到的值：" + value1);

        // 模拟一下数据过期的情况
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String valueExpired = cache.getIfPresent("关键词1");
        Assertions.assertNull(valueExpired);
    }

    @Test
    public void weightTest() {
        Cache<String, String> cache = Caffeine.newBuilder()
                .maximumWeight(10)
                .weigher((key, value) -> ((String) value).length())
                .build();

        cache.put("长文本", "abcdefg");
        cache.put("短文本", "abc");
        cache.put("超长文本", "abcdefg");

        System.out.println(cache.getIfPresent("长文本")); // 输出：abcdefg
        System.out.println(cache.getIfPresent("短文本")); // 输出：abc
        System.out.println(cache.getIfPresent("超长文本")); // 输出：null
    }

    @Test
    public void listenerTest() {
        Cache<String, String> cache = Caffeine.newBuilder()
                .maximumSize(10)
                .removalListener((key, value, cause) -> System.out.println("key:" + key + " value:" + value + " cause:" + cause))
                .build();

        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.invalidate("key2");
    }

    @Test
    public void listenerTest2() {
        RemovalListener<String, String> listener = (key, value, cause) ->
                System.out.println("被移除的键：" + key + ", 原因：" + cause);

        Cache<String, String> cache = Caffeine.newBuilder()
                .removalListener(listener)
                .build();

        cache.put("键1", "值1");
        cache.invalidate("键1"); // 手动移除，触发监听器
    }
}
