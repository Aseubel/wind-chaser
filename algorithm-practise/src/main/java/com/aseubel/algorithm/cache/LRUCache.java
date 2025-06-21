package com.aseubel.algorithm.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aseubel
 * @date 2025/6/20 下午5:53
 */
public class LRUCache implements Cache{

    private int capacity;
    private Node head;
    private Node tail;
    private int size;
    private final Map<Integer, Node> cache = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = tail;
        this.tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;
            if (size > capacity) {
                Node oldTail = removeTail();
                cache.remove(oldTail.key);
                size--;
            }
            return;
        }
        node.value = value;
        moveToHead(node);
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public class Node {
        int key;
        int value;
        Node prev;
        Node next;
        public Node() { }
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }
}
