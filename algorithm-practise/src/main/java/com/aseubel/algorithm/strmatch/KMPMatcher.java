package com.aseubel.algorithm.strmatch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/22 下午12:05
 * @description KMP算法匹配器
 * 时间复杂度 O(n+m)
 * 空间复杂度 O(m)
 */
public class KMPMatcher {

    /**
     * 使用 KMP 算法在文本中查找模式串的所有出现位置。
     *
     * @param text    文本字符串
     * @param pattern 模式字符串
     * @return 一个包含所有匹配起始索引的列表
     */
    public static List<Integer> kmpSearch(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (text == null || pattern == null || pattern.isEmpty() || text.length() < pattern.length()) {
            return result;
        }

        int n = text.length();
        int m = pattern.length();

        // 1. 预处理：为模式串构建 LPS 数组
        int[] lps = computeLPSArray(pattern);

        int i = 0; // 文本串的指针
        int j = 0; // 模式串的指针

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                // 找到了一个完整匹配
                // 匹配的起始索引是 i - j
                result.add(i - j);
                // 继续向后查找，利用 LPS 数组移动模式串
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                // 发生不匹配
                if (j != 0) {
                    // 利用 LPS 数组，将 j 回退到 lps[j-1] 的位置
                    // 注意：此时文本指针 i 不变
                    j = lps[j - 1];
                } else {
                    // 如果 j 已经是 0，说明模式串的第一个字符就不匹配
                    // 此时只能将文本指针 i 向后移动一位
                    i++;
                }
            }
        }
        return result;
    }

    /**
     * 计算模式串的 LPS (Longest Proper Prefix which is also Suffix) 数组。
     *
     * @param pattern 模式字符串
     * @return LPS 数组
     */
    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int length = 0; // 当前最长相等前后缀的长度
        int i = 1;      // 从模式串的第二个字符开始计算

        lps[0] = 0; // lps[0] 永远是 0

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    // 不匹配，回退 length
                    length = lps[length - 1];
                } else {
                    // length 已经是 0，说明没有公共前后缀
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

}
