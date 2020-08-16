/**
 * @(#)SortArraySearch.java, 2020/8/15.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

/**
 * 统计一个数字在排序数组中出现的次数。
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 50000
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 */
public class SortArraySearch {

    public int search(int[] nums, int target) {

        int len = nums.length;

        int result = 0;

        for (int i = 0; i < len; i++) {
            if (nums[i] == target) {
                result++;
            }

        }

        return result;
    }

    public static void main(String[] args) {
        SortArraySearch sortArraySearch = new SortArraySearch();
        System.out.println(sortArraySearch.search(new int[]{5,7,7,8,8,10}, 8) == 2);

        System.out.println(sortArraySearch.search(new int[0], 1) == 0);
    }
}