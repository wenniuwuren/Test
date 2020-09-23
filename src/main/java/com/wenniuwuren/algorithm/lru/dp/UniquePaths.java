/**
 * @(#)UniquePaths.java, 2020/9/17.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.dp;

public class UniquePaths {

    public int uniquePaths(int m, int n) {

        // 数组代表从坐标(x,y)走到结尾(m-1,n-1)可能的路径数量
        int[][] dp = new int[m][n];

        // 状态转移方程 dp[m][n] = dp[m][n+1] + dp[m+1][n]
        dp[m-1][n-1] = 1;

        // 因为只能向下，向右走，所以最后一列和最后一行路径可能数量都是1
        for (int c = 0; c < n; c++) {
            dp[m - 1][c] = 1;
        }

        for (int r = 0; r < m; r++) {
            dp[r][n - 1] = 1;
        }

        for (int r = m - 2; r >= 0; r--) {
            for (int c = n - 2; c >= 0; c--) {
                dp[r][c] = dp[r][c + 1] + dp[r + 1][c];
            }

        }

        return dp[0][0];

    }



    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(3, 2) == 3);
        System.out.println(uniquePaths.uniquePaths(7, 3) == 28);
    }
}