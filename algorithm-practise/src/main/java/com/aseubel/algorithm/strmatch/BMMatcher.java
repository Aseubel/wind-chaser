package com.aseubel.algorithm.strmatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/22 下午12:14
 * @description Boyer-Moore 算法（仅坏字符规则）
 * 时间复杂度：最好 O(n)，最坏 O(nm)。
 * 空间复杂度：O(1)，仅使用常数个额外空间。
 */
public class BMMatcher {
    private final static int ALPHABET_SIZE = 256;

    /**
     * 预处理：构建坏字符表。
     * 表中记录了每个字符在模式串中最后出现的位置。
     *
     * @param pattern 模式字符串
     * @return 坏字符表（一个整型数组）
     */
    private static int[] buildBadCharTable(String pattern) {
        int[] badChar = new int[ALPHABET_SIZE];
        // 初始化所有字符的位置为 -1，表示不存在
        Arrays.fill(badChar, -1);

        // 遍历模式串，记录每个字符最后出现的位置
        for (int i = 0; i < pattern.length(); i++) {
            badChar[pattern.charAt(i)] = i;
        }
        return badChar;
    }

    /**
     * 使用 Boyer-Moore 算法（仅坏字符规则）查找模式串。
     *
     * @param text    文本字符串
     * @param pattern 模式字符串
     * @return 一个包含所有匹配起始索引的列表
     */
    public static List<Integer> boyerMooreSearch(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (text == null || pattern == null || pattern.isEmpty() || text.length() < pattern.length()) {
            return result;
        }

        int m = pattern.length();
        int n = text.length();

        // 1. 预处理：构建坏字符表
        int[] badCharTable = buildBadCharTable(pattern);

        int s = 0; // s 是模式串在文本中对齐的起始位置（偏移量）
        while (s <= (n - m)) {
            int j = m - 1; // 从模式串的末尾开始比较

            // 2. 从右向左比较，直到发现不匹配或模式串完全匹配
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
                j--;
            }

            // 3. 判断结果
            if (j < 0) {
                // j < 0 表示模式串已完全匹配
                result.add(s);

                // 移动模式串以查找下一个匹配
                // 如果文本串已到末尾，则无法再移动
                // 否则，将模式串移动到其开头对齐文本串 s+m 位置的字符之后
                // 这是一个安全的移动，可以避免无限循环（例如，pattern="AAA", text="AAAAA"）
                s += (s + m < n) ? m - badCharTable[text.charAt(s + m)] : 1;
            } else {
                // 发生不匹配，使用坏字符规则计算位移
                char badChar = text.charAt(s + j);
                int shift = j - badCharTable[badChar];

                // 确保至少移动 1 位，防止负数或零位移
                s += Math.max(1, shift);
            }
        }
        return result;
    }
}
