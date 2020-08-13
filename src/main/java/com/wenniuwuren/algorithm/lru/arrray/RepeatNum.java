/**
 * @(#)RepeatNum.java, 2020/8/12.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

import java.util.HashSet;
import java.util.Set;

public class RepeatNum {

    public int findRepeatNumber(int[] nums) {
        int len = nums.length;

        int result = -1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
            } else {
                result = nums[i];
                break;
            }

        }

        return result;
    }

    public static void main(String[] args) {
        RepeatNum repeatNum = new RepeatNum();
        System.out.println(repeatNum.findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
    }
}