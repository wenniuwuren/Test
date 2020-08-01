/**
 * @(#)IntegerBreak.java, 2020/7/30.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.dp;

public class IntegerBreak {

    public int integerBreak(int n) {

        // dp[i] 为正整数i可以拆分后整数的最大乘积
        int[] dp = new int[n + 1];

        dp[1] = 0;
        dp[0] = 0;

        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));

            }

            dp[i] = curMax;
        }

        return dp[n];
    }
}