package com.aseubel.collection.queue;

/**
 * @author Aseubel
 * @date 2025/6/29 下午7:08
 * @description 循环队列
 * 可以解决假溢出问题，即尾指针超出范围但前面已经有元素出队，导致size < capacity时的溢出。
 */
public class CircleQueue {
    private int[] arr;
    private int front;
    private int rear;
    private int size;

    public CircleQueue(int capacity) {
        arr = new int[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return front == rear && size == 0;
    }

    public boolean isFull() {
        return (rear + 1) % arr.length == front && size == arr.length;
    }

    public boolean enqueue(int value) {
        if (isFull()) {
            System.out.println("队列已满");
            return false;
        }
        arr[rear] = value;
        rear = (rear + 1) % arr.length;
        size++;
        return true;
    }

    public Integer dequeue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return null;
        }
        int value = arr[front];
        front = (front + 1) % arr.length;
        size--;
        return value;
    }

    public void clear() {
        front = 0;
        rear = 0;
        size = 0;
        arr = new int[arr.length];
    }
}
