package com.aseubel.algorithm;

import com.aseubel.algorithm.sort.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/20 下午6:44
 */
@SpringBootTest
public class SortTest {

    private List<Integer> arr;

    @BeforeEach
    public void setUp() {
        arr = Arrays.asList(5, 3, 8, 6, 2, 7, 1, 4);
    }

    @Test
    public void bubbleSortTest() {
        Sort sort = new BubbleSort();
        sort.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        Assertions.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), arr);
    }

    @Test
    public void selectionSortTest() {
        Sort sort = new SelectSort();
        sort.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        Assertions.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), arr);
    }

    @Test
    public void insertionSortTest() {
        Sort sort = new InsertSort();
        sort.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        Assertions.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), arr);
    }

    @Test
    public void mergeSortTest() {
        MergeSort.mergeSort(arr, 0, arr.size() - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        Assertions.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), arr);
    }

    @Test
    public void heapSortTest() {
        Sort sort = new HeapSort();
        sort.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        Assertions.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), arr);
    }

    @Test
    public void quickSortTest() {
        QuickSort.quickSort(arr, 0, arr.size() - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        Assertions.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), arr);
    }
}
