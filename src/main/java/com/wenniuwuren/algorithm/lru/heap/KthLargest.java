/**
 * @(#)KthLargest.java, 2020/9/10.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.heap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class KthLargest {

    PriorityQueue<Integer> priorityQueue = null;
    int kth = 0;
    public KthLargest(int k, int[] nums) {
        priorityQueue = new PriorityQueue<>();
        kth = k;

        // 利用小根堆， 只保留k个最大的值，顶部就是第k大的元素
        for (int i = 0; i < nums.length; i++) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(nums[i]);
            } else {
                int top = priorityQueue.peek();
                if (top < nums[i]) {
                    priorityQueue.poll();
                    priorityQueue.add(nums[i]);
                }
            }

        }


    }

    public int add(int val) {
        if (priorityQueue.size() >= kth) {
            if (val > priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.add(val);
            }
        } else {
            priorityQueue.add(val);
        }

        return priorityQueue.peek();

    }


    public static void main(String[] args) {
//        KthLargest kthLargest = new KthLargest(3, new int[]{4,5,8,2});
//        System.out.println(kthLargest.add(3) == 4);
//        System.out.println(kthLargest.add(5) == 5);
//        System.out.println(kthLargest.add(10) == 5);
//        System.out.println(kthLargest.add(9) == 8);
//        System.out.println(kthLargest.add(4) == 8);


        KthLargest kthLargest = new KthLargest(1, new int[0]);
        System.out.println(kthLargest.add(-3) == -3);
        System.out.println(kthLargest.add(-2) == -2);
        System.out.println(kthLargest.add(-4) == -2);
        System.out.println(kthLargest.add(0) == 0);
        System.out.println(kthLargest.add(4) == 4);

//        KthLargest kthLargest = new KthLargest(2, new int[]{0});
//        System.out.println(kthLargest.add(-1) == -1);
//        System.out.println(kthLargest.add(1) == 0);
//        System.out.println(kthLargest.add(-2) == 0);
//        System.out.println(kthLargest.add(-4) == 0);
//        System.out.println(kthLargest.add(3) == 1);


    }
}