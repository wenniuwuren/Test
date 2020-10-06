/**
 * @(#)TwoSum.java, 2020/10/3.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.doublepoint;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *  
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
//        int[] result = new int[2];
//
//        // 双指针
//        int left = 0; int right = nums.length - 1;
//
//        while (left < right) {
//            int sum = nums[left] + nums[right];
//            if (sum == target) {
//                result[0] = left;
//                result[1] = right;
//                return result;
//            } else if (sum > target){
//                right--;
//            } else {
//                left++;
//            }
//
//        }
//
//        return result;
        // 上面的会破坏下标

        // x+y = target  我如果要找y值，则 y= target-x
        // ，用一个数据结构存储这个x和下标，这样遍历一遍就能找到
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        return new int[0];
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        System.out.println(twoSum.twoSum(new int[]{2, 7, 11, 15}, 9));
    }
}