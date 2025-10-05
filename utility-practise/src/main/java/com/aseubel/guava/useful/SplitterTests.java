package com.aseubel.guava.useful;

import com.google.common.base.Splitter;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/10/4 下午10:09
 */
public class SplitterTests {
    public static void main(String[] args) {
        String input = "Java, , Python , ,Go, ";
        List<String> langs = Splitter.on(",")
                .trimResults()           // 去除空格
                .omitEmptyStrings()      // 忽略空字符串
                .splitToList(input);

        System.out.println("结果：" + langs);  // [Java, Python, Go]
    }
}
