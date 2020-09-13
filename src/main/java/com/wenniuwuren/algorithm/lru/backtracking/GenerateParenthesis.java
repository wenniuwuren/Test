/**
 * @(#)GenerateParenthesis.java, 2020/9/12.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {

        List<String> results = new ArrayList<>();

        if (n == 0) return results;

       StringBuilder result = new StringBuilder();

        backtracking(results, result, n, n);
        return results;
    }

    private void backtracking(List<String> results, StringBuilder result, int leftRemain, int rightRemain) {
        if (leftRemain == 0 && rightRemain == 0) {
//            if (isValidateParenthesis(result)) {
                results.add(result.toString());
//            }
            return;
        }

        // 用完括号直接减枝
        if (leftRemain < 0 || rightRemain < 0) return;

        if (leftRemain > 0) {
            result.append("(");
            backtracking(results, result, leftRemain - 1, rightRemain);
            result.deleteCharAt(result.length() - 1);
        }

        // rightRemain > leftRemain 左括号先于右括号放，这样就能保住括号符合要求
        if (rightRemain > 0 && rightRemain > leftRemain) {
            result.append(")");
            backtracking(results, result, leftRemain, rightRemain - 1);
            result.deleteCharAt(result.length() - 1);
        }
    }

    private boolean isValidateParenthesis(Deque<String> result) {
        Deque<String> stack = new ArrayDeque<>();
        Iterator<String> iterator = result.iterator();

        while (iterator.hasNext()) {
            String str = iterator.next();
            if (")".equals(str)) {
                if (!stack.isEmpty()) {
                    if ("(".equals(stack.peek())) {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                stack.push(str);
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        System.out.println(generateParenthesis.generateParenthesis(3));
    }
}