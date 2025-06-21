package com.aseubel.algorithm.sort;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/20 下午11:43
 * @description 堆排序
 * 时间复杂度：最好 O(nlogn)，平均 O(nlogn)，最坏 O(nlogn)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 */
public class HeapSort implements Sort{

    @Override
    public int[] sort(int[] arr) {
        // 实现堆排序算法，首先构建最大堆
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        // 将堆顶元素（最大值）与末尾元素交换，并重新调整堆
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
        return arr;
    }

    private void heapify(int[] arr, int n, int i) {
        // 调整以i为根的子树，使其成为最大堆
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest])
            largest = l;
        if (r < n && arr[r] > arr[largest])
            largest = r;
        if (largest!= i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            // 递归调整受影响的子树
            heapify(arr, n, largest);
        }
    }

    @Override
    public void sort(List<Integer> arr) {
        int n = arr.size();
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr.get(0);
            arr.set(0, arr.get(i));
            arr.set(i, temp);
            heapify(arr, i, 0);
        }
    }

    private void heapify(List<Integer> arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && arr.get(l) > arr.get(largest))
            largest = l;
        if (r < n && arr.get(r) > arr.get(largest))
            largest = r;
        if (largest!= i) {
            int swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);
            heapify(arr, n, largest);
        }
    }
}
