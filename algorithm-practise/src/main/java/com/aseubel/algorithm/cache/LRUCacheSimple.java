package com.aseubel.algorithm.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Aseubel
 * @date 2025/6/20 下午6:26
 */
class LRUCacheSimple extends LinkedHashMap<Integer, Integer> implements Cache{
    private int capacity;

    public LRUCacheSimple(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return super.size() > capacity;
    }
}
