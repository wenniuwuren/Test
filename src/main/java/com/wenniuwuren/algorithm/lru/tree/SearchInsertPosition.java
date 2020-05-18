/**
 * @(#)SearchInsertPosition.java, 2020/5/18.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

import org.junit.Assert;

/**
 * 给定一个排好序的数组和一个目标值，如果目标值存在则返回下标。如果没有，则按顺序插入这个值并返回下标
 */
public class SearchInsertPosition {


    public int searchInsert(int[] nums, int target) {

        boolean asc = true;
        if (nums.length >= 2) {
            if (nums[0] < nums[1]) {
                asc = true;
            } else {
                asc = false;
            }
        }

        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }

            if (asc && target < nums[i]) {
                return i;
            }

            if (!asc && target > nums[i]) {
                return i;
            }

            if (nums.length - 1 == i) {
                return nums.length;
            }

        }

        return result;
    }


    public static void main(String[] args) {
        SearchInsertPosition searchInsertPosition = new SearchInsertPosition();

        int[] num1 = new int[]{1,3,5,6};
        Assert.assertEquals(2, searchInsertPosition.searchInsert(num1, 5));

        int[] num2 = new int[]{1,3,5,6};
        Assert.assertEquals(1, searchInsertPosition.searchInsert(num2, 2));

        int[] num3 = new int[]{1,3,5,6};
        Assert.assertEquals(4, searchInsertPosition.searchInsert(num2, 7));
    }
}