/**
 * @(#)SplitArray.java, 2020/8/1.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

import java.util.ArrayList;
import java.util.List;

/**
 * 将数组分为n份
 * 每份相差不超过1，多余的从前往后放
 *
 * case1： [1,2,3,4,5,6,7]  分为3份  [[1,2,3], [4,5],[6,7]]
 *
 * case2: [1,2] 分为 3分  [[1],[2],[]]
 *
 */
public class SplitArray {

    public List<List<Integer>> split(int[] array, int n) {

        List<List<Integer>> result = new ArrayList<>();

        if (array == null) {
            return result;
        }

        int len = array.length;

        int mod = len % n;
        int num = len / n;

        for (int i = 0, j = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            int count = 0;

            while (count < num && j < len) {
                list.add(array[j]);
                j++;
                count++;
            }

            if (mod > 0 && j < len) {
                list.add(array[j]);
                j++;
                mod--;
            }

            result.add(i, list);
        }

        return result;
    }

    public static void main(String[] args) {
        SplitArray splitArray = new SplitArray();

        // System.out.println(splitArray.split(new int[] {1,2,3,4,5,6,7},3));

//        System.out.println(splitArray.split(new int[]{1,2} , 3));

//        System.out.println(splitArray.split(new int[]{1 ,2}, 2));

        System.out.println(splitArray.split(new int[]{1, 2, 3}, 7));
    }

}