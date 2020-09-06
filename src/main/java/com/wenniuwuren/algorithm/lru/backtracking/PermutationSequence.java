/**
 * @(#)PermutationSequence.java, 2020/9/5.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {

    public String getPermutation(int n, int k) {

        List<String> results = new ArrayList<>();

        String oneToN = "";
        for (int i = 1; i <= n; i++) {
            oneToN += i;
        }

        boolean[] used = new boolean[oneToN.length()];
        char[] chars = oneToN.toCharArray();
        backtracking(chars, results, new StringBuilder(), used, 0, k);

        return results.get(k - 1);
    }

    private void backtracking(char[] chars, List<String> results, StringBuilder result, boolean[] used, int count, int k) {
        if (results.size() == k) { // 只要找出第k个就行。不然会超时
            return;
        }

        if (count == chars.length) { // 终止条件
            results.add(result.toString()); // 新建对象
            return; // 结束
        }

        for (int i = 0; i < chars.length; i++) {
            if (used[i]) {
                continue;
            }

            if (!used[i]) {
                used[i] = true; // 访问过
                result.append(chars[i]);
            }

            backtracking(chars, results, result, used, count + 1, k);

            used[i] = false;
            result.deleteCharAt(result.length() - 1);
        }

    }

    public static void main(String[] args) {
        PermutationSequence permutationSequence = new PermutationSequence();
        System.out.println(permutationSequence.getPermutation(3, 3).equals("213"));
        System.out.println(permutationSequence.getPermutation(4, 9).equals("2314"));
    }
}