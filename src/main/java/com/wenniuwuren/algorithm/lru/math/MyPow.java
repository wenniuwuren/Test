/**
 * @(#)MyPow.java, 2020/9/12.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.math;

/**
 * 50. Pow(x, n)
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *
 */
public class MyPow {

    // 快速幂方式.  x^0 = 1
    // 偶数 x -> x^2 -> x^4 -> x^8 -> x^16 -> x^32 -> x^64 = x^64
    // 级数 x -> x^2 -> x^4 -> x^8 -> x^16 -> x^32 -> x^64 多乘一个x = x^65
    public double myPow(double x, int n) {
        if (n >= 0) {
            return pow(x, n);
        } else {
            return 1 / pow(x, -n);
        }

    }

    private double pow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }

        double y = pow(x, n/2);
        return n % 2 == 0 ? y*y : y*y*x;
    }



    public static void main(String[] args) {
        MyPow myPow = new MyPow();
        System.out.println(myPow.myPow(2, 3) == 8.0);
        System.out.println(myPow.myPow(2.10000, 3));
        System.out.println(myPow.myPow(2.00000, -2) == 0.25000);
        // 0.00003
        //System.out.println(myPow.myPow(34.00515, -3));
    }
}