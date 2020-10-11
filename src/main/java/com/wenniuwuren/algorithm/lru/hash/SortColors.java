/**
 * @(#)SortColors.java, 2020/10/7.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 *
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 *
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 */
public class SortColors {

    // 时间复杂度O(n) 空间O(n)
    /*public void sortColors (int[] nums) {
        if (nums == null || nums.length == 0) return;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) map.put(nums[i], 0);
            map.put(nums[i], map.get(nums[i]) + 1);
        }

        int index = 0;
        for (int i = 0; i <= 2; i++) {
            Integer count = map.get(i);

            while (count != null && count != 0) {
                nums[index] = i;
                index++;
                count--;
            }
        }

    }*/

    public void sortColors (int[] nums) {
        if (nums == null || nums.length == 0) return;

        // 单指针，第一次循环把0放到前面， 第二次循环把1放到0后面

        // 每次循环交换的初始位置
        int swapIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[swapIndex];
                nums[swapIndex] = temp;
                swapIndex++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[swapIndex];
                nums[swapIndex] = temp;
                swapIndex++;
            }
        }

    }

    public static void main(String[] args) {
        SortColors sortColors = new SortColors();
        //sortColors.sortColors(new int[]{2,0,2,1,1,0});
        sortColors.sortColors(new int[]{0});
    }
}