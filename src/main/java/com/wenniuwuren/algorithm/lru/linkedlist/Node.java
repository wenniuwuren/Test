/**
 * @(#)Node.java, 2020/9/6.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.linkedlist;

public class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}