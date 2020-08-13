/**
 * @(#)ClimbStairs2.java, 2020/8/12.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.dp;

public class ClimbStairs2 {

    public int numWays(int n) {

        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        ClimbStairs2 climbStairs2 = new ClimbStairs2();
        System.out.println(climbStairs2.numWays(7) == 21);
    }
}