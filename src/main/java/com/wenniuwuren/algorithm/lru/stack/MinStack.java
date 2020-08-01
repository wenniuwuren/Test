/**
 * @(#)MinStack.java, 2020/8/1.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.stack;

import java.util.Stack;

public class MinStack {
    Stack<Integer> stack = null;
    Stack<Integer> minStack = null;
    Integer min = Integer.MAX_VALUE;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        min = Math.min(min, x);
        minStack.push(min);
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            minStack.pop();

            if (!minStack.isEmpty()) {
                min = minStack.peek();
            } else {
                min = Integer.MAX_VALUE;
            }

        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek();
        } else {
            return -1;
        }
    }

    public int getMin() {
        if (!minStack.isEmpty()) {
            return minStack.peek();
        }
        return -1;

    }

    public static void main(String[] args) {
        MinStack test = new MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//
//        System.out.println(minStack.getMin() == -3);
//        minStack.pop();
//        System.out.println(minStack.top() == 0);
//        System.out.println(minStack.getMin() == -2);

        test.push(2147483646);
        test.push(2147483646);
        test.push(2147483647);

        System.out.println(test.top() == 2147483647);
        test.pop();
        System.out.println(test.getMin() == 2147483646);

        test.pop();
        System.out.println(test.getMin() == 2147483646);
        test.pop();

        test.push(2147483647);
        System.out.println(test.top() == 2147483647);
        System.out.println(test.getMin() == 2147483647); // todo

        test.push(-2147483648);
        System.out.println(test.top() == -2147483648);
        System.out.println(test.getMin() == -2147483648);

        test.pop();
        System.out.println(test.getMin() == 2147483647); // ToDO




    }
}