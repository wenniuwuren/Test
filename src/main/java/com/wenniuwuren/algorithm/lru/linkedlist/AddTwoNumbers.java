/**
 * @(#)AddTwoNumbers.java, 2020/8/2.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：2 -> 9 -> 8 -> 3
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode r = result;

        int add = 0; // 进位
        while (l1 != null && l2!= null) {
            int currNum = (l1.val + l2.val + add)%10;
            add = (l1.val + l2.val + add)/10;

            result.next = new ListNode(currNum);

            // next
            result = result.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 == null && l2 != null) {
            int currNum = (l2.val + add)%10;
            add = (l2.val + add)/10;

            result.next = new ListNode(currNum);

            result = result.next;
            l2 = l2.next;
        }

        while (l2 == null && l1 != null) {
            int currNum = (l1.val + add)%10;
            add = (l1.val + add)/10;

            result.next = new ListNode(currNum);

            result = result.next;
            l1 = l1.next;
        }

        if (add != 0) { // 最后的进位只会加一次
            result.next = new ListNode(add);
        }


        return r.next;
    }

    public static void main(String[] args) {
       /* ListNode listNode1 = new ListNode(9);
        ListNode listNode2 = new ListNode(9);
        ListNode listNode3 = new ListNode(9);
        ListNode listNode4 = new ListNode(9);
        ListNode listNode8 = new ListNode(9);
        ListNode listNode9 = new ListNode(9);
        ListNode listNode10 = new ListNode(9);


        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode8;
        listNode8.next = listNode9;
        listNode9.next = listNode10;

        ListNode listNode5 = new ListNode(9);
        ListNode listNode6 = new ListNode(9);
        ListNode listNode7 = new ListNode(9);
        ListNode listNode11 = new ListNode(9);
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode11;*/

        ListNode listNode1 = new ListNode(5);
        ListNode listNode5 = new ListNode(5);

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        addTwoNumbers.addTwoNumbers(listNode1, listNode5);
    }
}