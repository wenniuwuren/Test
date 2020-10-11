/**
 * @(#)CanJump.java, 2020/10/7.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.greedy;

/**
 * 55. 跳跃游戏
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 */
public class CanJump {

    public boolean canJump(int[] nums) {
        if (nums == null) return false;

        if (nums.length == 0) return true;

        //表示每个下标 mostDistance 最大可到达距离 = nums[i]+i
        int mostDistance = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= mostDistance) {
                mostDistance = Math.max(mostDistance, nums[i] + i);

                if (mostDistance >= nums.length - 1) return true;
            }

        }


        return false;
    }

    public static void main(String[] args) {
        CanJump canJump = new CanJump();
        System.out.println(canJump.canJump(new int[]{2,3,1,1,4}) == true);

        System.out.println(canJump.canJump(new int[]{3,2,1,0,4}) == false);
    }
}