/**
 * @(#)ContainerWithMostWater.java, 2020/5/31.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.stack;

/**
 * Example:
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {

        int mostWater = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {

                int h = height[i];
                if (h > height[j]) {
                    h = height[j];
                }

                int tempWater = (j - i) * h;
                if (tempWater > mostWater) {
                    mostWater = tempWater;
                }

            }

        }

        return mostWater;
    }

    public int maxArea1(int[] height) {

        int l = 0; int r = height.length - 1;

        int area = 0;


        while (l < r) {

            int h = Math.min(height[l], height[r]);

            int tempArea = (r - l) * h;

            if (tempArea > area) {
                area = tempArea;
            }

            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }

        }

        return area;

    }

    public static void main(String[] args) {
        ContainerWithMostWater c = new ContainerWithMostWater();
        System.out.println(c.maxArea1(new int[]{1,8,6,2,5,4,8,3,7}) == 49);
    }
}