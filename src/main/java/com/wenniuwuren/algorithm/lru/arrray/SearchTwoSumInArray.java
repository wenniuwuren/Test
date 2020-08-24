/**
 * @(#)SearchTwoSumInArray.java, 2020/8/19.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

/**
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 *
 * 限制：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 */
public class SearchTwoSumInArray {

    // 两层循环会超时
    public int[] twoSum(int[] nums, int target) {

//        int len = nums.length;
//
//        int[] result = new int[2];
//        for (int i = 0; i < len; i++) {
//
//            int left = i + 1; int right = len - 1;
//
//            while (left <= right) {
//
//                int mid = left + (right - left)/2;
//
//                if (nums[i] + nums[mid] == target) {
//                    result[0] = nums[i];
//                    result[1] = nums[mid];
//                    break;
//                } else if (nums[i] + nums[mid] > target) {
//                    right = mid - 1;
//                } else {
//                    left = mid + 1;
//                }
//
//            }
//
//
//        }
//
//        return result;

        // 更好的方法是双指针
        int len = nums.length;
        int[] result = new int[2];

        int left = 0; int right = len - 1;

        while (left < right) {

            if (nums[left] + nums[right] == target) {
                result[0] = nums[left];
                result[1] = nums[right];
                break;
            } else if (nums[left] + nums[right] > target) {
                right--;
            } else {
                left++;
            }

        }

        return result;
    }

    public static void main(String[] args) {
        SearchTwoSumInArray sumInArray = new SearchTwoSumInArray();
        System.out.println(sumInArray.twoSum(new int[]{2,7,11,15}, 9));
    }
}