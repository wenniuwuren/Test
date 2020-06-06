/**
 * @(#)LongestConsecutiveSequence.java, 2020/6/6.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

import java.util.Arrays;

public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        int longest = 0;
        if (nums == null || nums.length == 0) {
            return longest;
        }

        if (nums.length == 1) {
            return 1;
        }

        Arrays.sort(nums);

        int pre = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++){
            if (pre == nums[i] - 1 || pre == nums[i]) {
                if (pre == nums[i] - 1) {
                    count++;
                    pre = nums[i];
                }

                if (i == nums.length - 1) {
                    if (count > longest) {
                        longest = count;
                    }

                }
            } else {
                if (count > longest) {
                    longest = count;
                }
                count = 1;
                pre = nums[i];
            }

        }


        return longest;

    }

    public static void main(String[] args) {
        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
        System.out.println(longestConsecutiveSequence.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}) == 4);

        System.out.println(longestConsecutiveSequence.longestConsecutive(new int[]{1,2,0,1}) == 3);

        System.out.println(longestConsecutiveSequence.longestConsecutive(new int[]{1,2,0,1,3,4}) == 5);

        System.out.println(longestConsecutiveSequence.longestConsecutive(new int[]{1,1,1,1,1}) == 1);

        System.out.println(longestConsecutiveSequence.longestConsecutive(new int[]{1,1,1,1,1}) == 1);


        System.out.println(longestConsecutiveSequence.longestConsecutive(new int[]{4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3}) == 5);

    }
}