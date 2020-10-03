/**
 * @(#)RemoveNthFromEnd.java, 2020/9/28.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.linkedlist;

/**
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 *
 *
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;

        ListNode virtualHead = new ListNode(-1);
        virtualHead.next = head;

        ListNode fast = virtualHead;
        ListNode slow = virtualHead;


        // fast 多走一步，slow就是指向目标节点前一个节点
        while (n+1 != 0) {
            fast = fast.next;
            n--;
        }

        // 定位到目标节点的前一个节点
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 删除目标节点
        slow.next = slow.next.next;

        return virtualHead.next;
    }

    public static void main(String[] args) {
        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();

        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        removeNthFromEnd.removeNthFromEnd(listNode1, 2);

//        ListNode listNode1 = new ListNode(1);
//        removeNthFromEnd.removeNthFromEnd(listNode1, 1);
    }
}