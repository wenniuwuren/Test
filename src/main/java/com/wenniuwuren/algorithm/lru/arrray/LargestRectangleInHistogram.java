/**
 * @(#)LargestRectangleInHistogram.java, 2020/5/30.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        // brute force
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int largestArea = 0;

        for (int i = 0; i < heights.length; i++) {
            int start = 0;
            int end = heights.length - 1;
            for (int left = i - 1; left > -1; left--) {
                if (heights[left] < heights[i]) {
                    start = left + 1;
                    break;
                }

                if (left == 0) {
                    start = left;
                    break;
                }

            }

            for (int right = i + 1; right < heights.length; right++) {
                if (heights[right] < heights[i]) {
                    end = right - 1;
                    break;
                }

                if (right == heights.length - 1) {
                    end = right;
                    break;
                }

            }

            int tempArea = heights[i] * (end - start + 1);
            if (tempArea > largestArea) {
                largestArea = tempArea;
            }
        }

        return largestArea;

    }

    public static void main(String[] args) {
        LargestRectangleInHistogram l = new LargestRectangleInHistogram();

        System.out.println(l.largestRectangleArea(new int[]{2,1,5,6,2,3}) == 10);

        System.out.println(l.largestRectangleArea(new int[]{2}) == 2);

        System.out.println(l.largestRectangleArea(new int[]{1,1}) == 2);

        System.out.println(l.largestRectangleArea(new int[]{0, 2, 0}) == 2);

        System.out.println(l.largestRectangleArea(new int[]{0, 9}) == 9);

        System.out.println(l.largestRectangleArea(new int[]{12,11,10,9,8,7,6,5,4,3,2,1}) == 42);
    }
}