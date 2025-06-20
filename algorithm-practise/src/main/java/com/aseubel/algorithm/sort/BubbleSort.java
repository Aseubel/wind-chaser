package com.aseubel.algorithm.sort;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/20 下午6:23
 * @description 冒泡排序
 * 时间复杂度：最好 O(n)，最坏 O(n^2)，平均 O(n^2)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 */
public class BubbleSort implements Sort{

    @Override
    public int[] sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    @Override
    public void sort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    // swap arr[j] and arr[j+1]
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }
}
