/**
 * @(#)NQueens.java, 2020/9/13.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *  
 *
 * 示例：
 *
 * 输入：4
 * 输出：[
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *  
 *
 * 提示：
 *
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 *
 */
public class NQueens2 {

    public int totalNQueens(int n) {

        // 列是否被使用
        Set<Integer> colExist = new HashSet<Integer>();

        // 斜线限制： 目标节点坐标i+j 不能等于 其他皇后节点i+j
        Set<Integer> diagonals1 = new HashSet<Integer>();

        // 反斜线限制: 目标节点坐标i-j 不能等于 其他皇后节点i-j
        Set<Integer> diagonals2 = new HashSet<Integer>();

        String[][] matrix = new String[n][n];

        List<List<String>> result = new ArrayList<>();

        backtracking(matrix, result, n, 0, colExist, diagonals1, diagonals2);

        return result.size();
    }

    private void backtracking(String[][] matrix, List<List<String>> result, int n, int currRow, Set<Integer> colExist,
        Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (currRow == n) {
            // 超过最后一行就终止了
            result.add(generateString(matrix));
        }

        // 遍历列
        for (int j = 0; j < n; j++) {
            int diag1 = currRow + j;
            int diag2 = currRow - j;

            // 列没用过，斜线没占用，反斜线没占用
            if (!colExist.contains(j) && !diagonals1.contains(diag1) && !diagonals2.contains(diag2)) {
                matrix[currRow][j] = "Q";

                colExist.add(j);
                diagonals1.add(diag1);
                diagonals2.add(diag2);

                backtracking(matrix, result, n, currRow + 1, colExist, diagonals1, diagonals2);

                colExist.remove(j);
                diagonals1.remove(diag1);
                diagonals2.remove(diag2);
                matrix[currRow][j] = null;
            }

        }



    }


    private List<String> generateString(String[][] matrix) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < matrix[0].length; j++) {
                if ("Q".equals(matrix[i][j])) {
                    temp.append("Q");
                } else {
                    temp.append(".");
                }
            }
            result.add(temp.toString());
        }

        return result;
    }

    public static void main(String[] args) {
        NQueens2 nQueens = new NQueens2();
        System.out.println(nQueens.totalNQueens(4));
    }
}