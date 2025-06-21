package com.aseubel.algorithm;

import com.aseubel.algorithm.cache.LFUCache;
import com.aseubel.algorithm.cache.LRUCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Aseubel
 * @date 2025/6/20 下午6:43
 */
@SpringBootTest
public class CacheTest {

    @Test
    public void LRUTest() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        Assertions.assertEquals(1, cache.get(1));
        cache.put(3, 3);
        Assertions.assertEquals(-1, cache.get(2));
        cache.put(4, 4);
        Assertions.assertEquals(-1, cache.get(1));
        Assertions.assertEquals(3, cache.get(3));
        Assertions.assertEquals(4, cache.get(4));
    }

    @Test
    public void LFUTest() {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        Assertions.assertEquals(1, cache.get(1));
        cache.put(3, 3);
        Assertions.assertEquals(-1, cache.get(2));
        Assertions.assertEquals(3, cache.get(3));
        cache.put(4, 4);
        Assertions.assertEquals(-1, cache.get(1));
        Assertions.assertEquals(3, cache.get(3));
        Assertions.assertEquals(4, cache.get(4));
    }
}
