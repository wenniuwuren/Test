/**
 * @(#)QuickSort.java, 2020/7/30.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.sort;

public class QuickSort {

    // 单向扫描划分方式
    public void forwardScanSort(int[] items) {
        forwardScanSort(items, 0, items.length - 1);

        System.out.println();
    }


    private void forwardScanSort(int[] items, int start, int end) {
        if (start < end) {
            int pivot = items[start];
            int i = start, j = start + 1;
            while (j <= end) {
                if (items[j] < pivot) {
                    i++;
                    swap(items, i, j);
                }
                j++;
            }
            swap(items, start, i);
            forwardScanSort(items, start, i - 1);
            forwardScanSort(items, i + 1, end);
        }
    }

    private void swap(int[] items, int a, int b) {
        int temp = items[a];
        items[a] = items[b];
        items[b] = temp;

    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        quickSort.forwardScanSort(new int[]{3, 7, 9, 1, 2});
    }
}