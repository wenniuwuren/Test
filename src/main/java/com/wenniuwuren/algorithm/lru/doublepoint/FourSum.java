/**
 * @(#)FourSum.java, 2020/10/5.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.doublepoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 *
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 */
public class FourSum {

    // Two sum  返回下标就用 map，返回值就用排序+双指针 O(n)
    // Three sum  一层for循环 + 双指针  O(n^2)
    // Four sum  两层for循环 + 双指针  O(n^3)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 4) return result;

        // 因为不是返回数组下标，就能排序+双指针
        Arrays.sort(nums);


        // 双指针去掉一层循环
        for (int a = 0; a < nums.length - 3; a++) {

            // 起始点相同，则会找到相同的. 只能从后一个节点往前排除相同的，不然会跳过可能的情况。
            if (a > 0 && nums[a] == nums[a - 1]) continue;

            for (int b = a + 1; b < nums.length - 2; b++) {

                // a 相同，如果b相同则可能找到相同的4元组
                if (b > a+1 && nums[b] == nums[b-1]) continue;

                int left = b + 1; int right = nums.length - 1;

                while (left < right) {
                    int sum = nums[a] + nums[b] + nums[left] + nums[right];

                    if (target == sum) {
                        List<Integer> list = new ArrayList<>(Arrays.asList(nums[a], nums[b], nums[left], nums[right]));
                        result.add(list);

                        // 前2个位置固定，如果剩余两个有一个一样要么找到重复的4元组，要么没找到.
                        while (left + 1 < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;

                        while (right - 1 > left && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (target > sum) {
                        left++;
                    } else {
                        right--;
                    }


                }


            }


        }

        return result;
    }

    public static void main(String[] args) {
        FourSum fourSum = new FourSum();
        // [-1,  0, 0, 1],
        //  [-2, -1, 1, 2],
        //  [-2,  0, 0, 2]
        System.out.println(fourSum.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));

        // [-2,-1,-1,1,1,2,2]  0
        // [[-2,-1,1,2],[-1,-1,1,1]]

        //System.out.println(fourSum.fourSum(new int[]{-2,-1,-1,1,1,2,2}, 0));
    }
}