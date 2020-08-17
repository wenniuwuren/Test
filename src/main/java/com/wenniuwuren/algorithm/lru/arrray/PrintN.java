/**
 * @(#)PrintN.java, 2020/8/16.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 */
public class PrintN {
    public int[] printNumbers(int n) {

//        List<Integer> list = new ArrayList<>();
//
//        int range = 1;
//
//        for (int i = 0; i < n; i++) {
//            range = 10 * range;
//        }
//
//
//        for (int i = 1; i < range; i++) {
//            list.add(i);
//        }
//
//        int len = list.size();
//        int[] result = new int[len];
//
//        for (int i = 0; i < len; i++) {
//            result[i] = list.get(i);
//        }
//        return result;

        int range = (int)Math.pow(10, n);
        int[] result = new int[range - 1];
        for (int i = 0; i < range - 1; i++) {
            result[i] = i + 1;
        }

        return result;

    }

    public static void main(String[] args) {
        PrintN printN = new PrintN();
        System.out.println(printN.printNumbers(3));
    }
}