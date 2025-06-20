package com.aseubel.algorithm.cache;

/**
 * @author Aseubel
 * @date 2025/6/20 下午6:26
 */
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
