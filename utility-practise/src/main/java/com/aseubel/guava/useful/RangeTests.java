package com.aseubel.guava.useful;

import com.google.common.collect.Range;

/**
 * @author Aseubel
 * @date 2025/10/4 下午8:27
 */
public class RangeTests {
    public static void main(String[] args) {
        // 创建一个闭区间 [60, 100]
        Range<Integer> passRange = Range.closed(60, 100);
        // 判断分数是否在区间内
        boolean isPass = passRange.contains(85); // true
        System.out.println("85分是否在区间内：" + isPass);
        // 创建一个开区间 (80, 90)
        Range<Integer> goodRange = Range.open(80, 90);
        System.out.println("87分是良好吗：" + goodRange.contains(87)); // true
    }
}
