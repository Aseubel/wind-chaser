package com.aseubel.algorithm.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aseubel
 * @date 2025/6/21 上午11:54
 */
public class LFUCache {

    private int capacity;
    private int size;
    private int minFreq;
    private Map<Integer, Node> cache = new HashMap<>();
    private Map<Integer, DoublyLinkedList> freqMap = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        int freq = node.freq;
        moveToNextFreqList(node);
        // 如果当前链表为空，我们需要在哈希表中删除，且更新minFreq
        if (freqMap.get(freq).size == 0) {
            freqMap.remove(freq);
            if (minFreq == freq) {
                minFreq++;
            }
        }
        node.freq++;
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            // 先看大小，满则淘汰
            if (size == capacity) {
                DoublyLinkedList minList = freqMap.get(minFreq);
                Node oldestNode = minList.removeTail();
                cache.remove(oldestNode.key);
                if (minList.size == 0) {
                    freqMap.remove(minFreq);
                }
                size--;
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            DoublyLinkedList oneList = freqMap.getOrDefault(1, new DoublyLinkedList());
            oneList.addToHead(newNode);
            freqMap.put(1, oneList);
            size++;
            minFreq = 1;
        } else {
            // 与get的流程相似
            int freq = node.freq;
            moveToNextFreqList(node);
            if (freqMap.get(freq).size == 0) {
                freqMap.remove(freq);
                if (minFreq == freq) {
                    minFreq++;
                }
            }
            node.value = value;
            node.freq++;
        }
    }

    private void moveToNextFreqList(Node node) {
        DoublyLinkedList oldList = freqMap.get(node.freq);
        oldList.removeNode(node);
        DoublyLinkedList newList = freqMap.getOrDefault(node.freq + 1, new DoublyLinkedList());
        freqMap.put(node.freq + 1, newList);
        newList.addToHead(node);
    }

    public class Node {
        int key;
        int value;
        int freq;
        Node prev;
        Node next;
        public Node() { }
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
            this.freq = 1;
        }
    }

    public class DoublyLinkedList {
        private Node head, tail;
        private int size;

        public DoublyLinkedList() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public Node getHead() {
            return head.next;
        }

        public Node getTail() {
            return tail.prev;
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
            size++;
        }

        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
    }
}
