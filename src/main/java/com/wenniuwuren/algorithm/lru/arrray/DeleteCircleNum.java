/**
 * @(#)DeleteCircleNum.java, 2020/8/16.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;



/**
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 *
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *
 * 限制：
 *
 * 1 <= n <= 10^5
 * 1 <= m <= 10^6
 *
 * 示例 2：
 *
 * 输入: n = 10, m = 17
 * 输出: 2
 */
public class DeleteCircleNum {

    public int lastRemaining(int n, int m) {

        return recursion(n, m);
    }

    /**
     * 求 给定n，时候留下的下标
     * @param n
     * @param m
     * @return
     */
    private int recursion(int n, int m) {
        if (n == 1) {
            return 0;
        }

        int x = recursion(n - 1, m);

        return (x + m)%n;
    }
}