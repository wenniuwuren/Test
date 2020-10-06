/**
 * @(#)SearchRotateSortedArray.java, 2020/10/6.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.binarysearch;

/**
 * 81. 搜索旋转排序数组 II
 *
 *假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 *
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 *
 * 示例 1:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 *
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 *
 *
 */
public class SearchRotateSortedArray {

    public boolean search(int[] nums, int target) {
        // 10111 和 1110111101 这种。此种情况下 nums[left] == nums[mid]，
        // 分不清到底是前面有序还是后面有序，此时 left++ 即可。相当于去掉一个重复的干扰项。
        // 数字重复带来的情况
        if (nums == null || nums.length == 0) return false;

        int left = 0; int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left)/2;

            if (nums[mid] == target) {
                return true;
            }

            // 重复的而且上面判断过 !=target，就跳过继续二分
            if (nums[left] == nums[mid]) {
                left++;
                continue;
            }

            if (nums[left] <= nums[mid]) {
                // left is sorted
                if (nums[left] <= target && nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

        }


        return false;
    }

    public static void main(String[] args) {
        SearchRotateSortedArray sortedArray = new SearchRotateSortedArray();
        System.out.println(sortedArray.search(new int[]{2,5,6,0,0,1,2}, 0) == true);
        System.out.println(sortedArray.search(new int[]{2,5,6,0,0,1,2}, 3) == false);

        System.out.println(sortedArray.search(new int[]{3, 1}, 1) == true);

        System.out.println(sortedArray.search(new int[]{1, 3, 1, 1, 1}, 3) == true);
    }
}