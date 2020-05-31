/**
 * @(#)RemoveDuplicatesSortedArray.java, 2020/5/30.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

/**
 * Given nums = [1,1,2], return 2
 *
 * Given nums = [0,0,1,1,1,2,2,3,3,4], return 5
 *
 * 只能原地删除， 不能借助新数组，只能O（1）空间复杂度
 */
public class RemoveDuplicatesSortedArray {


    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (nums == null || length == 0) {
            return 0;
        }

        for (int i = 0; i < length; i++) {

            int firstDupIndex = 0; boolean firstDup = false;
            int step = 0;
            for (int j = i + 1; j < length; j++) {

                if (nums[i] == nums[j]) {

                    if (!firstDup) {
                        firstDupIndex = j;
                        firstDup = true;
                    }

                    step++;
                }



                if (nums[i] != nums[j] && firstDup) {
                    nums[firstDupIndex] = nums[j];
                    firstDupIndex++;
                }
            }


            length = length - step;
        }

        return length;

    }

    public static void main(String[] args) {
        RemoveDuplicatesSortedArray r = new RemoveDuplicatesSortedArray();

//        System.out.println(r.removeDuplicates(new int[]{1,1,2}) == 2);
//
//        System.out.println(r.removeDuplicates(new int[]{1}) == 1);
//
//        System.out.println(r.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}) == 5);
//
//        System.out.println(r.removeDuplicates(new int[]{1, 1, 1, 1}) == 1);

        System.out.println(r.removeDuplicates(new int[]{1, 2}) == 2);

    }
}