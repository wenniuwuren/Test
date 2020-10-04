/**
 * @(#)oinChange.java, 2020/9/24.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.dp;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 *  
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *  
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        // 类似上楼梯， 走到第amount阶梯.  dp[i]表示走到i阶梯使用最少的步数其实就是硬币数量
        // dp[i] = min(dp[i - counts[j]] + 1, dp[i])   +1就是第i步走的一次
        int[] dp = new int[amount + 1];

        int max = amount + 1; // 最大步数 这样如果最后结果dp[amount] > amount 说明没被更新就是没找到结果
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
                }

            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange change = new CoinChange();
        System.out.println(change.coinChange(new int[]{1, 2, 5}, 11));
    }
}