/**
 * @(#)DetectCycle.java, 2020/10/11.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.linkedlist;

/**
 * 142. 环形链表 II
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 *
 * 说明：不允许修改给定的链表。
 *
 * 进阶：
 *
 * 你是否可以不用额外空间解决此题？
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 */
public class DetectCycle {

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        // 快慢指针 快的每次走两步，慢的走一步。 如果有环最终会相遇
        ListNode slow = head;
        ListNode fast = head;


        while (slow != null && fast != null) {
            slow = slow.next;

            if (fast.next != null) {
                fast = fast.next;
                fast = fast.next;
            } else {
                return null;
            }

            // 官方解释: 设链表中环外部分的长度为 a。slow 指针进入环后，又走了 b 的距离与fast 相遇。
            // 此时，fast 指针已经走完了环的 n 圈，因此它走过的总距离为 a+n(b+c)+b=a+(n+1)b+nc。
            // 根据题意，任意时刻，fast 指针走过的距离都为slow 指针的 2 倍。因此，我们有
            // a+(n+1)b+nc=2(a+b) -> a=c+(n−1)(b+c)
            // 我们会发现：从相遇点到入环点的距离加上 n-1n−1 圈的环长，恰好等于从链表头部到入环点的距离。
            //
            //因此，当发现 slow 与 fast 相遇时，我们再额外使用一个指针ptr。起始，它指向链表头部；
            // 随后，它和 slow 每次向后移动一个位置。最终，它们会在入环点相遇。

            // 我们发现 a==c slow继续走到和ptr相等就是入环位置.
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    slow = slow.next;
                    ptr = ptr.next;
                }
                return ptr;
            }
        }

        return null;

    }

    public static void main(String[] args) {
        DetectCycle detectCycle = new DetectCycle();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        listNode2.next = listNode1;
        System.out.println(detectCycle.detectCycle(listNode1));
    }
}