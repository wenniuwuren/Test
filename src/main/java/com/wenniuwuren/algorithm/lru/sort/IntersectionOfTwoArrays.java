/**
 * @(#)IntersectionOfTwoArrays.java, 2020/5/23.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.sort;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> result = new HashSet<>();
        int[] resultArray = null;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    result.add(nums1[i]);
                }

            }

        }

        Object[] array = result.toArray();
        resultArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            resultArray[i] = (Integer) array[i];
        }

        return resultArray;
    }

    public static void main(String[] args) {
        IntersectionOfTwoArrays intersectionOfTwoArrays = new IntersectionOfTwoArrays();

//        System.out.println(intersectionOfTwoArrays.intersection(new int[]{1,2,2,1}, (new int[]{2,2})));

        System.out.println(intersectionOfTwoArrays.intersection(new int[]{4,9,5}, (new int[]{9,4,9,8,4})));
    }
}