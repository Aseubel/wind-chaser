package com.aseubel.guava.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;

/**
 * @author Aseubel
 * @date 2025/10/4 下午2:59
 */
public class BiMapTests {
    public static void main(String[] args) {
        BiMap<String, Integer> userId = HashBiMap.create();
        userId.put("Alice", 1);
        userId.put("Bob", 2);
        userId.put("Charlie", 3);
        System.out.println(userId);
        System.out.println(userId.inverse());
        System.out.println(userId.get("Alice"));
        System.out.println(userId.inverse().get(1));

        // ImmutableBiMap is also available
         ImmutableBiMap<String, Integer> immutableUserId = ImmutableBiMap.copyOf(userId);
         System.out.println(immutableUserId);
    }
}
