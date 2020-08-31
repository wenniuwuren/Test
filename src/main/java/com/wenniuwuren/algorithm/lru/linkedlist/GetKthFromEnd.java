/**
 * @(#)GetKthFromEnd.java, 2020/8/31.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.linkedlist;

public class GetKthFromEnd {

    // 普通的遍历找出长度然后 len-k 解法
    public ListNode getKthFromEnd(ListNode head, int k) {

        if (head == null) {
            return null;
        }

        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        int substract = count - k;
        while (head != null && substract != 0) {
            substract--;
            head = head.next;
        }

        return head;
    }

    // 快慢指针解法
    public int kthToLast(ListNode head, int k) {

        ListNode slow = head;
        ListNode fast = head;

        // 快的先移动 k 步，然后快慢一起移动，当fast移动到最后null 返回slow
        while (k != 0) {
            fast = fast.next;
            k--;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow.val;

    }

    public static void main(String[] args) {
        GetKthFromEnd getKthFromEnd = new GetKthFromEnd();


        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

//        getKthFromEnd.getKthFromEnd(listNode1, 2);
        System.out.println(getKthFromEnd.kthToLast(listNode1, 2));
    }
}