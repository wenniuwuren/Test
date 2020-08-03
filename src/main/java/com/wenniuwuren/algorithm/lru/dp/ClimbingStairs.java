/**
 * @(#)ClimbingStairs.java, 2020/8/3.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.dp;

public class ClimbingStairs {

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];

        if (n < 2) {
            return 1;
        }

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbStairs(2) == 2);
        System.out.println(climbingStairs.climbStairs(3) == 3);
    }

}