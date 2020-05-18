/**
 * @(#)SortList.java, 2020/5/18.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.sort;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 *
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 *
 *
 * 此问题有两个难点：
 *
 * 确定链表的中点以进行两路归并。
 * 两路归并。
 * 首先进行中点的确认，用的是快慢指针的方法，快指针走两步，慢指针走一步，那么在遍历完链表的时候，慢指针指向的位置就是中点。
 * 但是需要注意的是，由于链表是通过遍历来确定长度的，所以再确认完中点之后，需要切开链表，所以此时还需要用一个变量来保存中点节点的前驱，用以切断链表。
 *
 *
 */
public class SortList {

    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode mid = slow.next;
        slow.next = null; // 从中间节点切断

        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        ListNode result = merge(left, right);

        return result;
    }

    ListNode merge(ListNode left, ListNode right) {
        ListNode virtualHead = new ListNode(0);

        ListNode result = virtualHead;
        while (left != null && right != null) {

            if (left.val < right.val) {
                virtualHead.next = left;
                left = left.next;
            } else {
                virtualHead.next = right;
                right = right.next;
            }

            virtualHead = virtualHead.next;
        }

        if (right == null) {
            virtualHead.next = left;
        }

        if (left == null) {
            virtualHead.next = right;
        }

        return result.next;
    }

    void printListNode(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + "->");
            listNode = listNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SortList sortList = new SortList();

        ListNode listNode1 = new ListNode(4);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(1);
        ListNode listNode4 = new ListNode(3);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;


        sortList.printListNode(listNode1);

        sortList.printListNode(sortList.sortList(listNode1));
    }
}