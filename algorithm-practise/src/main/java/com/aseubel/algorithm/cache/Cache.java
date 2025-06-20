package com.aseubel.algorithm.cache;

/**
 * @author Aseubel
 * @date 2025/6/20 下午6:37
 */
public interface Cache {

    int get(int key);

    void put(int key, int value);
}
