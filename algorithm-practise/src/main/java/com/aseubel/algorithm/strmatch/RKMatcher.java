package com.aseubel.algorithm.strmatch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/21 下午4:13
 * @description RK algorithm for string matching
 * 利用哈希算法实现的字符串匹配算法，时间复杂度O(n+m)，n为待匹配字符串的长度，m为模式字符串的长度
 */
public class RKMatcher {
    /**
     * 文本中字母表的基数（例如，对于ASCII，可以是256）。
     */
    private final static int d = 256;

    /**
     * 一个大的素数，用于哈希计算取模，以减少哈希冲突。
     */
    private final static int q = 101;

    /**
     * 使用 Rabin-Karp 算法在文本中查找模式串的所有出现位置。
     *
     * @param text    文本字符串
     * @param pattern 模式字符串
     * @return 一个包含所有匹配起始索引的列表
     */
    public static List<Integer> indexOf(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (text == null || pattern == null || pattern.isEmpty() || text.length() < pattern.length()) {
            return result;
        }

        int m = pattern.length();
        int n = text.length();
        int pHash = 0; // 模式串的哈希值
        int tHash = 0; // 文本窗口的哈希值
        int h = 1;

        // 计算 h = d^(m-1) % q
        // h 用于在滚动哈希中移除最高位的字符
        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % q;
        }

        // 1. 计算模式串的哈希值和文本第一个窗口的哈希值
        for (int i = 0; i < m; i++) {
            pHash = (d * pHash + pattern.charAt(i)) % q;
            tHash = (d * tHash + text.charAt(i)) % q;
        }

        // 2. 在文本中滑动模式串窗口
        for (int i = 0; i <= n - m; i++) {
            // 如果哈希值匹配，则进行逐字符比较以确认
            if (pHash == tHash) {
                int j;
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }
                if (j == m) {
                    result.add(i);
                }
            }

            // 3. 计算下一个窗口的滚动哈希值
            if (i < n - m) {
                // 移除旧的首位字符，加入新的末位字符
                tHash = (d * (tHash - text.charAt(i) * h) + text.charAt(i + m)) % q;

                // 确保 tHash 是正数
                if (tHash < 0) {
                    tHash = (tHash + q);
                }
            }
        }
        return result;
    }
}
