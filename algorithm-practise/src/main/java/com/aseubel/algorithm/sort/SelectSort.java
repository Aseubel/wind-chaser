package com.aseubel.algorithm.sort;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/20 下午6:34
 * @description 选择排序
 * 时间复杂度：最好 O(n^2)，平均 O(n^2)，最坏 O(n^2)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 */
public class SelectSort implements Sort{

    @Override
    public int[] sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            // 找到未排序部分的最小元素的索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换找到的最小元素和未排序部分的第一个元素
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    @Override
    public void sort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            // 找到未排序部分的最小元素的索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j) < arr.get(minIndex)) {
                    minIndex = j;
                }
            }
            // 交换找到的最小元素和未排序部分的第一个元素
            int temp = arr.get(minIndex);
            arr.set(minIndex, arr.get(i));
            arr.set(i, temp);
        }
    }

}
