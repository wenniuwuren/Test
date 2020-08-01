/**
 * @(#)LinkedListCycle.java, 2020/8/1.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {

    // leetcode 141 找链表中的环
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        Set<ListNode> set = new HashSet<>();

        while (head != null) {
            if (set.contains(head)) {
                return true;
            }

            set.add(head);
            head = head.next;
        }

        return false;
    }

    public static void main(String[] args) {
        LinkedListCycle listCycle = new LinkedListCycle();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        listNode2.next = listNode1;
        System.out.println(listCycle.hasCycle(listNode1));
    }
}