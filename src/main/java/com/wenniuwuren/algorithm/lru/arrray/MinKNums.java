/**
 * @(#)MinKNums.java, 2020/8/26.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

import java.util.Arrays;

public class MinKNums {

    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        Arrays.sort(arr);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }

        return result;
    }


}