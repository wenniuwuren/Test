package com.wenniuwuren.java8.inaction;

import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Created by hzzhuyibin on 2017/5/27.
 */
public class FactorialTest {

    public static void main(String[] args) {
        System.out.println(factorialByIterator(3));
        System.out.println(factorialByRecursive(3));
        System.out.println(factorialByByStream(3));


    }

    static int factorialByIterator(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * 函数式编程推荐使用递归，因为递归不会改变传入的参数值。但是递归每次调用方法都会创建方法栈，对内存消耗较大
     * @param n
     * @return
     */
    static int factorialByRecursive(int n) {
        if (n < 1) {
            return 1;
        }
        return n * factorialByIterator(n-1);

    }

    static int factorialByByStream(int n) {
        return IntStream.rangeClosed(1, n).reduce(1, (a, b) -> a * b);
    }

}

