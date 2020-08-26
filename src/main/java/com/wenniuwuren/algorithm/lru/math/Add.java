/**
 * @(#)Add.java, 2020/8/26.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.math;

/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 *  
 *
 * 示例:
 *
 * 输入: a = 1, b = 1
 * 输出: 2
 *  
 *
 * 提示：
 *
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 *
 */
public class Add {

    public int add(int a, int b) {
        /**
         * 设两数字的二进制形式 a,b ，其求和 s = a + b, a(i) 代表 a 的二进制第 i 位，则分为以下四种情况
         *  a(i) b(i)  无进位和  进位
         *   0    0       0      0
         *   1    0       1      0
         *   0    1       1      0
         *   1    1       0      1
         *
         *   可以看出来 无进位和 与 异或运算 规律相同，进位 和 与运算 规律相同（并需左移一位）
         *
         *   计算a+b，等价于(a^b)+((a&b)<<1)。
         * 由于公式中又出现了+号，因此要再重复 这个等价的计算过程。
         * 结束条件是：((a&b)<<1) 进位=0 这样+号就消除了.
         */

        if (a == 0) {
            return b;
        }

        if (b == 0) {
            return a;
        }

        int plus1 = (a&b) << 1;
        int plus2 = a^b;
        return add(plus1, plus2);
    }

    public static void main(String[] args) {
        Add add = new Add();
        System.out.println(add.add(1, 2));
    }
}