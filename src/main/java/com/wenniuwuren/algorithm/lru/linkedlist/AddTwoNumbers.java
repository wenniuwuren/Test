/**
 * @(#)AddTwoNumbers.java, 2020/8/2.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<ListNode> stack1 = new ArrayDeque<>();
        Deque<ListNode> stack2 = new ArrayDeque<>();

        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }
        ListNode nextNode = null;
        // 是否需要进一位
        boolean isEnterOne = false;
        while (!stack1.isEmpty() || !stack2.isEmpty() || isEnterOne) {
            int sum = 0;

            if (isEnterOne) {
                sum = sum + 1;
                isEnterOne = false;
            }

            if (stack1.isEmpty() && !stack2.isEmpty()) {
                sum += stack2.pop().val;
            }

            if (!stack1.isEmpty() && stack2.isEmpty()) {
                sum += stack1.pop().val;
            }

            if (!stack1.isEmpty() && !stack2.isEmpty()) {
                sum += stack1.pop().val + stack2.pop().val;
            }


            if (sum >= 10) {
                isEnterOne = true;
                sum = sum - 10;
            }

            if (nextNode == null) {
                nextNode = new ListNode(sum);
            } else {
                ListNode currentNode = new ListNode(sum);
                currentNode.next = nextNode;
                nextNode = currentNode;
            }

        }

        return nextNode;

    }

    public static void main(String[] args) {
        /*ListNode listNode1 = new ListNode(7);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(4);
        listNode5.next = listNode6;
        listNode6.next = listNode7;*/

        ListNode listNode1 = new ListNode(5);
        ListNode listNode5 = new ListNode(5);

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        addTwoNumbers.addTwoNumbers(listNode1, listNode5);
    }
}