package com.aseubel.guava.useful;

import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Aseubel
 * @date 2025/10/4 下午10:11
 */
public class JoinerTests {
    public static void main(String[] args) {
        // todo 考虑过滤掉空字符串
        List<String> items = Arrays.asList("苹果", null, "香蕉", "橙子", "");

        String result = Joiner.on("、")
                .useForNull("无")
//                .skipNulls()
                .join(items);

        System.out.println("拼接结果：" + result); // 苹果、无、香蕉、橙子、
    }
}
