/**
 * @(#)CopyRandomList.java, 2020/9/6.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {

    public Node copyRandomList(Node head) {
        Node node = null;
        if (head == null) {
            return node;
        }

        Node oldHead = head;
        node = new Node(head.val);

        // oldObject newObject
        Map<Node, Node> map = new HashMap<>();
        map.put(head, node);

        while (head != null) {

            if (head.next != null) {
                Node tempNode = map.get(head.next);

                if (tempNode == null) {
                    node.next = new Node(head.next.val);
                } else {
                    node.next = tempNode;
                }
                map.put(head.next, node.next);
            }

            if (head.random != null) {
                Node tempNode = map.get(head.random);

                if (tempNode == null) {
                    node.random = new Node(head.random.val);
                } else {
                    node.random = tempNode;
                }

                map.put(head.random, node.random);
            }

            // traverse next node
            head = head.next;
            node = node.next;
        }

        return map.get(oldHead);
    }

    public static void main(String[] args) {
        CopyRandomList copyRandomList = new CopyRandomList();
        Node node1 = new Node(7);

        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);

        node1.random = null;
        node1.next = node2;

        node2.next = node3;
        node2.random = node1;

        node3.next = node4;
        node3.random = node5;

        node4.next = node5;
        node4.random = node3;

        node5.next = null;
        node5.random = node1;


        System.out.println(copyRandomList.copyRandomList(node1));
    }

}