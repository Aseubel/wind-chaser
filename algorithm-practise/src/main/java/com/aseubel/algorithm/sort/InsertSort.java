package com.aseubel.algorithm.sort;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/20 下午6:34
 * @description 插入排序
 * 时间复杂度：最好 O(n)，平均 O(n^2)，最坏 O(n^2)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 */
public class InsertSort implements Sort{

    @Override
    public int[] sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // 将 key 插入到已排序部分的正确位置
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    @Override
    public void sort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 1; i < n; i++) {
            int key = arr.get(i);
            int j = i - 1;

            // 将 key 插入到已排序部分的正确位置
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, key);
        }
    }

}
