/**
 * @(#)TwoMultiplyMax.java, 2020/5/31.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

public class TwoMultiplyMax {

    public int maxProduct(int[] nums) {

        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }

        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int temp = (nums[i]-1)*(nums[j]-1);
                if (temp > result) {
                    result = temp;
                }

            }

        }

        return result;
    }

    public static void main(String[] args) {
        TwoMultiplyMax twoMultiplyMax = new TwoMultiplyMax();
        System.out.println(twoMultiplyMax.maxProduct(new int[]{3,4,5,2}) == 12);

        System.out.println(twoMultiplyMax.maxProduct(new int[]{1,5,4,5}) == 16);

        System.out.println(twoMultiplyMax.maxProduct(new int[]{3,7}) == 21);
    }
}