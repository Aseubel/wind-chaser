package com.aseubel.collection.queue;

import java.util.AbstractQueue;

/**
 * @author Aseubel
 * @date 2025/6/29 下午7:08
 * @description 循环队列
 * 可以解决假溢出问题，即尾指针超出范围但前面已经有元素出队，导致size < capacity时的溢出。
 */
public class CircleQueue<T> {
    private Object[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CircleQueue(int capacity) {
        if(capacity <= 0) {
            System.out.println("队列容量必须大于0");
            return;
        } else {
            this.capacity = capacity + 1;
        }
        arr = new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }

    public boolean enqueue(T value) {
        if (isFull()) {
            System.out.println("队列已满");
            return false;
        }
        arr[rear] = value;
        rear = (rear + 1) % capacity;
        size++;
        return true;
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return null;
        }
        T value = (T) arr[front];
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    public void clear() {
        front = 0;
        rear = 0;
        size = 0;
        arr = new Object[capacity];
    }
}
