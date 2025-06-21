package com.aseubel.algorithm.strmatch;

/**
 * @author Aseubel
 * @date 2025/6/21 下午4:12
 * @description Brute-Force 也就是暴力匹配算法
 */
public class BFMatcher {
    public static int indexOf(String str, String target) {
        int n = str.length();
        int m = target.length();
        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (str.charAt(i + j)!= target.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                return i;
            }
        }
        return -1;
    }
}
