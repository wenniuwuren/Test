/**
 * @(#)SlidingWindowMaximum.java, 2020/9/10.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.heap;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *  
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 *  
 *
 * 示例:
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
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) { //滑动窗口为1，则返回原数组
            return nums;
        }

        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); //双端队列
        int maxIndex = 0;

        //初始化队列
        for (int i = 0; i < k; i++) {
            cleanQueue(deque, nums, i, k);

            deque.add(i); //存下标 ，下标可以快速找到value
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        result[0] = nums[maxIndex];

        // 遍历剩余队列
        for (int i = k, j = 1; i < nums.length; i++, j++) {

            cleanQueue(deque, nums, i, k);

            deque.add(i);
            // 因为前面小的都被删了，所以拿到的就是最大的
            result[i - k + 1] = nums[deque.getFirst()];
        }

        return result;
    }

    private void cleanQueue(Deque<Integer> deque, int[] nums, int i, int k) {
        // 移除不在滑动窗口的数据
        if (!deque.isEmpty() && deque.getFirst() == i - k) {
            deque.removeFirst();
        }

        // num[i]是加到queue最后的，只要比num[i]，小的都可以删除。
        // 为了让最大值在最左边， 当前元素左边去考虑删除，即last
        while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) {
            deque.removeLast();
        }

    }

    public static void main(String[] args) {
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
//        System.out.println(slidingWindowMaximum.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)); //[3,3,5,5,6,7]
//        System.out.println(slidingWindowMaximum.maxSlidingWindow(new int[]{7,2,4}, 2)); // [7,4]

        // [3,3,2,5]
        System.out.println(slidingWindowMaximum.maxSlidingWindow(new int[]{1,3,1,2,0,5}, 3));
    }
}