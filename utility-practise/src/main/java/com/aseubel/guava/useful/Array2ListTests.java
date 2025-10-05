package com.aseubel.guava.useful;

import com.alibaba.nacos.shaded.com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aseubel
 * @date 2025/10/4 下午8:09
 */
public class Array2ListTests {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        List<Integer> list = Ints.asList(arr);
        System.out.println(list);
        int maxn = Ints.max(arr);
        System.out.println("maxn: " + maxn);
        int minn = Ints.min(arr);
        System.out.println("minn: " + minn);
        boolean contains = Ints.contains(arr, 3);
        System.out.println("contains 3: " + contains);
    }
}
