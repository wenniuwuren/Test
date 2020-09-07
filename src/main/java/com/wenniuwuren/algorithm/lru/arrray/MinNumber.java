/**
 * @(#)MinNumber.java, 2020/9/6.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

import java.util.Arrays;

/**
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [10,2]
 * 输出: "102"
 * 示例 2:
 *
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *
 */
public class MinNumber {

    public String minNumber(int[] nums) {
        String result = "";
        if (nums == null || nums.length == 0) {
            return result;
        }

        String[] numsStr = new String[nums.length];

        for (int i = 0; i < numsStr.length; i++) {
            numsStr[i] = nums[i] + "";
        }

        Arrays.sort(numsStr, (x, y) -> (x + y).compareTo(y + x));

        for (int i = 0; i < numsStr.length; i++) {
            result += numsStr[i];
        }

        return result;
    }
}