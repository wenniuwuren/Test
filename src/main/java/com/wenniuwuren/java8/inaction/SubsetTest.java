package com.wenniuwuren.java8.inaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 输入一个列表获取列表的子集，如 {1, 4, 9} -> [[], [9], [4], [4, 9], [1], [1, 9], [1, 4], [1, 4, 9]]
 * Created by hzzhuyibin on 2017/5/27.
 */
public class SubsetTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 4, 9);
        System.out.println(subsets(list));
    }

    static List<List<Integer>> subsets(List<Integer> list) {
        // 如果空列表，子集为空列表自身
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }

        // 取出一个，和剩下的列表
        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());

        List<List<Integer>> subans = subsets(rest);
        // 将剩下的列表subans2，再加上取出来的first
        List<List<Integer>> subans2 = insertAll(first, subans);
        return concat(subans, subans2);
    }

    private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> subans) {
        List<List<Integer>> result = new ArrayList<>();

        for (List<Integer> list : subans) {
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }
        return result;

    }

    private static List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
        // 以下改变了 传入参数，不推荐使用
//        a.addAll(b);
//        return a;

        List<List<Integer>> r = new ArrayList<>(a);
        r.addAll(b);
        return r;
    }

}

