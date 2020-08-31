/**
 * @(#)NumsOccurInArray.java, 2020/8/29.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

import java.util.Arrays;

public class NumsOccurInArray {


    /*public int[] singleNumbers(int[] nums) {
        Arrays.sort(nums);

        int len = nums.length;


        int[] result = new int[2];

        Integer oneOfIt = null;

        for (int i = 0; i < len; i++) {
            // 和左边不同且和右边不同则是这个数字.
            if (i - 1 >= 0) {
                if (nums[i] != nums[i - 1]) {
                    if (i + 1 < len) {
                        if (nums[i] != nums[i + 1]) {

                            if (oneOfIt == null) {
                                oneOfIt = nums[i];
                            } else {
                                result[1] = nums[i];
                            }
                        }
                    } else {
                        if (oneOfIt == null) {
                            oneOfIt = nums[i];
                        } else {
                            result[1] = nums[i];
                        }
                    }
                }
            } else {
                if (i + 1 < len) {
                    if (nums[i] != nums[i + 1]) {

                        if (oneOfIt == null) {
                            oneOfIt = nums[i];
                        } else {
                            result[1] = nums[i];
                        }
                    }
                } else {
                    if (oneOfIt == null) {
                        oneOfIt = nums[i];
                    } else {
                        result[1] = nums[i];
                    }
                }
            }


        }

        result[0] = oneOfIt;

        return result;
    }*/


    public int[] singleNumbers(int[] nums) {
        /**
         * 异或满足交换律，第一步异或，相同的数其实都抵消了，剩下两个不同的数。
         * 这两个数异或结果肯定有某一位为1，不然都是0的话就是相同数。找到这个位，
         * 不同的两个数一个在此位为0，另一个为1。按此位将所有数分成两组，分开后各自异或，相同的两个数异或肯定为0（而且分开的时候，两个数必为一组）。
         * 剩下的每组里就是我门要找的数。
         */
        int ab = 0;
        for (int num : nums) {
            ab ^= num;
        }

        //获得k中最低位的1 。 因为任何数与1& 可以确定最后一位
        int mask = 1;

        while ((ab & mask) == 0) {
            mask = mask << 1;
        }

        int a = 0;
        int b = 0;

        for (int num : nums) {
            if ((num & mask) == 0) {
                a = a ^ num;
            } else {
                b = b ^ num;
            }
        }

        return new int[]{a,b};
    }




    public static void main(String[] args) {
        NumsOccurInArray numsOccurInArray = new NumsOccurInArray();
//        System.out.println(numsOccurInArray.singleNumbers(new int[]{1, 2}));

//        System.out.println(numsOccurInArray.singleNumbers(new int[]{4, 1, 4, 6}));
        System.out.println(numsOccurInArray.singleNumbers(new int[]{1,2,10,4,1,4,3,3}));
    }
}