package com.aseubel.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Aseubel
 * @date 2025/6/29 下午7:18
 */
@SpringBootTest
@Slf4j
public class QueueTest {
    @Test
    public void arrayDequeueTest() {
        // 会扩容，底层是循环数组
        Queue<Integer> queue = new ArrayDeque<>(3);
        // offer()添加元素，如果队列已满，返回false
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        Assertions.assertTrue(queue.offer(4)); // true, 因为扩容了
        // peek()获取队首元素，poll()获取队首元素并删除
        System.out.println(queue.peek()); // 1
        System.out.println(queue.poll()); // 1
        System.out.println(queue.peek()); // 2
        System.out.println(queue.poll()); // 2
        // 如果为空返回null
        System.out.println(queue.peek());
        System.out.println(queue.poll());

        queue.clear();

        // add()添加元素，如果队列已满，抛出异常
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        // remove()删除队首元素，但是如果为空会抛出异常
        queue.remove();
        queue.forEach(System.out::println);
    }

    @Test
    public void priorityQueueTest() {
        // 由此可知queue默认小顶堆，且不是加入后自动排序
        Queue<Integer> queue = new PriorityQueue<>();
        List<Integer> list = new ArrayList<>();
        genElements(queue);
        // 按照队列的顺序输出
        list.addAll(queue);
        queue.forEach(System.out::print);
        System.out.println();

        list.clear();

        genElements(queue);
        // 取出按照优先队列的优先级顺序取出
        for (int i = 0; i < 4; i++) {
            list.add(queue.poll());
        }
        list.forEach(System.out::print);
        System.out.println();
    }

    @Test
    public void blockingQueueTest() throws InterruptedException {
        // 阻塞队列，可以指定队列大小，队列满时，生产者线程阻塞，队列空时，消费者线程阻塞
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        // 开一个消费者线程，从队列中取出元素
        Thread consumer = new Thread(() -> {
            try {
                for (int i=0;i < 5;i++) {
                    log.info("消费者取出元素 {}", queue.take());
                    log.info("开始等待下一个元素");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 开一个生产者线程，向队列中添加元素
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    log.info("等我0.1秒");
                    Thread.sleep(100);
                    log.info("生产者添加元素 {}", i);
                    queue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
    }

    private void genElements(Queue<Integer> queue) {
        queue.offer(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(4);
    }
}
