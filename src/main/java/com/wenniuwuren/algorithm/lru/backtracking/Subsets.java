/**
 * @(#)Subsets.java, 2020/9/20.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        List<Integer> list = new ArrayList<>();

        // [] 空list 也是子集合
        result.add(new ArrayList<>(list));

        backtracking(result, list, nums, 0);

        return result;
    }

    private void backtracking(List<List<Integer>> result, List<Integer> list, int[] nums, int curr) {
        if (list.size() >= 1) {
            result.add(new ArrayList<>(list));
        }

        for (int i = curr; i < nums.length; i++) {
            list.add(nums[i]);

            backtracking(result, list, nums, i + 1);

            list.remove(list.size() - 1);

        }

    }

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        System.out.println(subsets.subsets(new int[]{1, 2, 3}));
    }
}