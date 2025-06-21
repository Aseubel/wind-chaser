package com.aseubel.algorithm.ratelimiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Aseubel
 * @date 2025/6/21 下午3:27
 * @description 漏桶算法限流器
 */
public class LeakyBucketRateLimiter {
    // 创建一个固定大小的阻塞队列作为漏桶
    private final BlockingQueue<Integer> bucket;
    // 水桶的容量
    private static final int BUCKET_CAPACITY = 100;
    // 以固定的速率漏水，每x毫秒移出一个元素
    private static final long LEAK_RATE = 50;

    public LeakyBucketRateLimiter() {
        bucket = new ArrayBlockingQueue<>(BUCKET_CAPACITY);
        // 启动一个线程以恒定速率从队列中移除元素
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("接收到: " + bucket.take());;
                    Thread.sleep(LEAK_RATE);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    // 尝试将数据包放入漏桶中
    public boolean addPacket(int packet) {
        return bucket.offer(packet);
    }

}
