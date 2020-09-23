/**
 * @(#)MaxProduct.java, 2020/9/23.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.dp;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 */
public class MaxProduct {

    public int maxProduct(int[] nums) {
        // dp[i] 以下标i为终止位置的最大子数组乘积
        // dp[i] = max(nums[i], dp[i-1]*num[i])   乘法和加法不一样..乘法负负得正

        int[] maxdp = new int[nums.length];
        int[] mindp = new int[nums.length];
        maxdp[0] = nums[0];

        int result = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            // 当nums[i]>=0 则 maxdp[i-1]*nums[i]会最大，当<0，则
            maxdp[i] = Math.max(maxdp[i-1]*nums[i],    Math.max(mindp[i-1]*nums[i], nums[i]));

            mindp[i] = Math.min(mindp[i-1]*nums[i],    Math.min(maxdp[i-1]*nums[i], nums[i]));

            result = Math.max(result, maxdp[i]);
        }

        result = Math.max(result, maxdp[0]);

        return result;
    }

    public static void main(String[] args) {
        MaxProduct maxProduct = new MaxProduct();
        System.out.println(maxProduct.maxProduct(new int[]{2,3,-2,4}) == 6);
        System.out.println(maxProduct.maxProduct(new int[]{-2,0,-1}) == 0);
        System.out.println(maxProduct.maxProduct(new int[]{-2}) == -2);

        System.out.println(maxProduct.maxProduct(new int[]{-2,3,-4}) == 24);
    }
}