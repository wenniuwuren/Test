/**
 * @(#)SubsetsWithDup.java, 2020/9/20.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 */
public class SubsetsWithDup {

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        // 方便去重复
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
//        Set<List<Integer>> result = new HashSet<>();
        List<Integer> list = new ArrayList<>();


        backtracking(result, list, nums, 0);

//        List<List<Integer>> r = new ArrayList<>();

//        Iterator<List<Integer>> iterator = result.iterator();
//
//        while (iterator.hasNext()) {
//            r.add(iterator.next());
//        }

        return result;
    }

    private void backtracking(List<List<Integer>>  result, List<Integer> list, int[] nums, int curr) {

        result.add(new ArrayList<>(list));

        for (int i = curr; i < nums.length; i++) {
            if (i > curr && nums[i] == nums[i - 1]) continue;

            list.add(nums[i]);
            backtracking(result, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        SubsetsWithDup subsetsWithDup = new SubsetsWithDup();
        System.out.println(subsetsWithDup.subsetsWithDup(new int[]{1, 2, 2}));
    }
}