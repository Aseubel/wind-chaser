package com.aseubel.algorithm.sort;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/20 下午11:09
 * @description 归并排序
 * 时间复杂度：最好 O(nlogn) 平均 O(nlogn) 最坏 O(nlogn)
 * 空间复杂度：O(n)
 * 稳定性：稳定
 */
public class MergeSort {

    public static void mergeSort(List<Integer> arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(List<Integer> arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (arr.get(i) <= arr.get(j)) {
                temp[k++] = arr.get(i++);
            } else {
                temp[k++] = arr.get(j++);
            }
            while (i <= mid) {
                temp[k++] = arr.get(i++);
            }
            while (j <= right) {
                temp[k++] = arr.get(j++);
            }
            for (int l = 0; l < temp.length; l++) {
                arr.set(left + l, temp[l]);
            }
        }
    }
}
