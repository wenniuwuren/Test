/**
 * @(#)MySqrt.java, 2020/9/8.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.math;

public class MySqrt {

    public int mySqrt(int x) {

        if (x == 0 || x == 1) {
            return x;
        }

        int left = 0; int right = x;
        int result = 0;
        while(left <= right) {
            int mid = left + (right - left)/2;

            if (mid == x/mid) { // 防止mid* mid 越界
                return mid;
            } else if (mid >= x/mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
                result = mid; // 不一定能走到==，可能在小于的区间里
            }

        }

        return result;
    }

    public static void main(String[] args) {
        MySqrt mySqrt = new MySqrt();
        System.out.println(mySqrt.mySqrt(3));
    }
}