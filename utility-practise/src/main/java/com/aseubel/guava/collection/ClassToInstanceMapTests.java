package com.aseubel.guava.collection;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

/**
 * @author Aseubel
 * @date 2025/10/4 下午6:24
 */
public class ClassToInstanceMapTests {
    public static void main(String[] args) {
        ClassToInstanceMap<Object> map = MutableClassToInstanceMap.create();
        map.putInstance(String.class, "Hello");
        map.putInstance(Integer.class, 123);
        System.out.println(map.getInstance(String.class));
        System.out.println(map.getInstance(Integer.class));
    }
}
