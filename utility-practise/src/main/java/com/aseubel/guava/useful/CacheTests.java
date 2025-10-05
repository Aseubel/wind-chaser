package com.aseubel.guava.useful;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Aseubel
 * @date 2025/10/4 下午10:40
 */
public class CacheTests {
    public static void main(String[] args) throws ExecutionException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .refreshAfterWrite(5, TimeUnit.MINUTES) // 5分钟自动刷新
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        // 模拟数据库或远程服务调用
                        return "数据:" + key;
                    }
                });

        // 取数据，自动缓存
        String value = cache.get("article_123");
        System.out.println("获取内容：" + value);
    }
}
