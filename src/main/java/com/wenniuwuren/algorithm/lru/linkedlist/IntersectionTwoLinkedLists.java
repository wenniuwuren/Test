/**
 * @(#)IntersectionTwoLinkedLists.java, 2020/8/1.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class IntersectionTwoLinkedLists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Set<ListNode> set = new HashSet<>();

        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            } else {
                headB = headB.next;
            }

        }

        return null;
    }
}