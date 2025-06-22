package com.aseubel.algorithm.strmatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/22 下午12:57
 * @description Sunday 算法
 * 时间复杂度：最好 O(n)， 最坏 O(nm)
 * 空间复杂度：O(1)
 */
public class SundayMatcher {
    private final static int ALPHABET_SIZE = 256; // 假设为 ASCII

    /**
     * 使用 Sunday 算法在文本中查找模式串的所有出现位置。
     *
     * @param text    文本字符串
     * @param pattern 模式字符串
     * @return 一个包含所有匹配起始索引的列表
     */
    public static List<Integer> sundaySearch(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (text == null || pattern == null || pattern.isEmpty() || text.length() < pattern.length()) {
            return result;
        }

        int n = text.length();
        int m = pattern.length();

        // 1. 预处理：构建位移表 (Shift Table)
        int[] shift = new int[ALPHABET_SIZE];
        // 步骤 1.1: 初始化默认位移为 m + 1
        Arrays.fill(shift, m + 1);
        // 步骤 1.2: 为模式串中的每个字符计算其位移
        // 如果字符出现多次，后面的会覆盖前面的，正好得到该字符最后一次出现时的位移
        for (int i = 0; i < m; i++) {
            shift[pattern.charAt(i)] = m - i;
        }

        int i = 0; // i 是文本串中对齐窗口的起始指针
        while (i <= n - m) {
            int j = 0; // j 是模式串的指针

            // 2. 从左到右比较当前窗口
            while (j < m && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }

            // 3. 判断是否匹配
            if (j == m) {
                // j == m 说明模式串已完全匹配
                result.add(i);
            }

            // 4. 计算下一次位移 (关键步骤)
            // 检查窗口后是否有足够的字符来确定位移
            if (i + m < n) {
                // 获取“哨兵”字符
                char sentinel = text.charAt(i + m);
                // 根据哨兵字符在位移表中的值来移动 i
                i += shift[sentinel];
            } else {
                // 已经到达文本末尾，无法再进行下一次匹配，退出循环
                break;
            }
        }
        return result;
    }
}
