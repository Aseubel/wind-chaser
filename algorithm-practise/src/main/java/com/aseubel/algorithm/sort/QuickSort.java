package com.aseubel.algorithm.sort;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/20 下午10:53
 * @description 快速排序
 * 时间复杂度：最好 O(nlogn) 平均 O(nlogn) 最坏 O(n^2)
 * 空间复杂度：O(logn)
 * 稳定性：不稳定
 */
public class QuickSort {

    public static void quickSort(List<Integer> arr, int l, int r) {
        int n = r - l + 1;
        if (n <= 1) {
            return;
        }
        int pivot = arr.get(l);
        int i = l + 1;
        int j = r;
        while (i <= j) {
            while (i <= j && arr.get(i) <= pivot) {
                i++;
            }
            while (i <= j && arr.get(j) >= pivot) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }
        arr.set(l, arr.get(j));
        arr.set(j, pivot);
        quickSort(arr, l, j - 1);
        quickSort(arr, j + 1, r);
    }

    private static void swap(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}
