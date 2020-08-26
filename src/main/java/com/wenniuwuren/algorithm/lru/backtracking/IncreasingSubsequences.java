/**
 * @(#)IncreasingSubsequences.java, 2020/8/26.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 *
 * 示例:
 *
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 说明:
 *
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 *
 */
public class IncreasingSubsequences {

    public List<List<Integer>> findSubsequences(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        Set<List<Integer>> resultSet = new HashSet<>();



        backtracking(nums, 0, Integer.MIN_VALUE, resultSet, new ArrayList<>());


        Iterator<List<Integer>> iterator = resultSet.iterator();

        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        return result;
    }

    /**
     *
     * @param nums
     * @param curr 当前位置
     * @param pre 上一个数值
     * @param resultSet
     */
    private void backtracking(int[] nums, int curr, Integer pre, Set<List<Integer>> resultSet, List<Integer> list) {
        // 回溯结束条件
        if (curr == nums.length) {
            if (list.size() >= 2) {
                resultSet.add(new ArrayList<>(list)); // 注意新建对象，不然相同对象是覆盖
            }
            return;
        }

        /**
         * 使序列合法的办法非常简单，即给「选择」做一个限定条件，只有当前的元素大于等于上一个选择的元素的时候才能选择这个元素，这样枚举出来的所有元素都是合法的
         *
         * 那如何保证没有重复呢？我们需要给「不选择」做一个限定条件，只有当当前的元素不等于上一个选择的元素的时候，
         * 才考虑不选择当前元素，直接递归后面的元素。因为如果有两个相同的元素，我们会考虑这样四种情况：
         *
         * 前者被选择，后者被选择
         * 前者被选择，后者不被选择
         * 前者不被选择，后者被选择
         * 前者不被选择，后者不被选择
         * 其中第二种情况和第三种情况其实是等价的，我们这样限制之后，舍弃了第二种，保留了第三种，于是达到了去重的目的。
         *
         */

        // 如果选择当前元素
        if (nums[curr] >= pre) {
            list.add(nums[curr]);
            backtracking(nums, curr + 1, nums[curr], resultSet, list);
            list.remove(list.size() - 1);
        }

        // 如果不选择当前元素
        if (nums[curr] != pre) {
            backtracking(nums,curr + 1, pre, resultSet, list);
        }

    }

    public static void main(String[] args) {
        IncreasingSubsequences increasingSubsequences = new IncreasingSubsequences();
        System.out.println(increasingSubsequences.findSubsequences(new int[]{4, 6, 7, 7}));

//        System.out.println(increasingSubsequences.findSubsequences(new int[]{4, 3, 2, 1}));
    }
}