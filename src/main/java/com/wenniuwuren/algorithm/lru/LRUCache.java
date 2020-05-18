package com.wenniuwuren.algorithm.lru;

import java.util.HashMap;


public class LRUCache {

    Node head, tail;
    HashMap<Integer, Node> map;
    int size;

    class Node{
        int val;
        Node next, prev;
        int key;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }

        void deleteNode(){
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }

        void insertNode(Node head){
            this.next = head.next;
            head.next = this;
            this.prev = head;
            this.next.prev = this;
        }
    }

    public LRUCache(int capacity) {
        this.size = capacity;
        map = new HashMap<>();

        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
    }

    public int get(int key) {
        Node node = map.get(key);
        if(node == null) return -1;

        node.deleteNode();
        node.insertNode(head);

        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if(node != null){
            node.val = value;
            node.deleteNode();
            node.insertNode(head);

            return;
        }

        if(map.size() == size){
            node = tail.prev;
            node.deleteNode();
            map.remove(node.key);
        }

        node = new Node(key, value);
        node.insertNode(head);
        map.put(key, node);
    }

    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(2);

        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));      // returns 1
        lruCache.put(3, 3);    // evicts key 2
        System.out.println(lruCache.get(2));       // returns -1 (not found)
        lruCache.put(4, 4);    // evicts key 1
        System.out.println(lruCache.get(1));       // returns -1 (not found)
        System.out.println(lruCache.get(3));       // returns 3
        System.out.println(lruCache.get(4));      // returns 4

    }

}

