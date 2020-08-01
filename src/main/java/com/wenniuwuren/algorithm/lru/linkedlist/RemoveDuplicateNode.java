/**
 * @(#)RemoveDuplicateNode.java, 2020/6/26.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.linkedlist;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RemoveDuplicateNode {

    // 不能排序，顺序要和原来保持一致
    public ListNode removeDuplicateNodes(ListNode head) {
        if(head == null) {
            return null;
        }

        ListNode resultHead = head;
        Set<Integer> set = new HashSet<>();
        ListNode preNode = null;
        while (head != null) {
            if (!set.contains(head.val)) {
                set.add(head.val);
                preNode = head;
            } else {
                preNode.next = head.next;
            }

            head = head.next;
        }


        return resultHead;
    }

    public static void main(String[] args) {
        RemoveDuplicateNode removeDuplicateNode = new RemoveDuplicateNode();

        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(2);

        ListNode listNode4 = new ListNode(3);
        ListNode listNode5 = new ListNode(2);
        ListNode listNode6 = new ListNode(1);
        ListNode listNode7 = new ListNode(4);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;

        // 3->1->2->4
        System.out.println(removeDuplicateNode.removeDuplicateNodes(listNode1));
    }
}