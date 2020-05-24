/**
 * @(#)SlideWindowMax.java, 2020/5/24.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.slidewindow;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 */
public class SlideWindowMax {

    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums.length == 0 || nums == null){
            return new int[0];
        }

        int[] result = new int[nums.length - k + 1];

        if (nums.length == 1) {
            result[0]=nums[0];
            return result;
        }


        for (int i = 0; i < nums.length - k + 1; i++) {
            int maxValue = nums[i];
            for (int j = i; j < i + k; j++) {
                if (nums[j] > maxValue) {
                    maxValue = nums[j];
                }

            }

            result[i] = maxValue;

        }

        return result;

    }

    public static void main(String[] args) {
        SlideWindowMax slideWindowMax = new SlideWindowMax();
        System.out.println(slideWindowMax.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3));
    }


}