package com.aseubel.cache.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Aseubel
 * @date 2025/8/1 下午9:13
 */
@Slf4j
@Component
public class RedissonDelayQueue {

    @Autowired
    private RedissonClient redissonClient;

    private RDelayedQueue<String> delayQueue;
    private RBlockingQueue<String> blockingQueue;
    private ExecutorService executorService;

    public RedissonDelayQueue() {
        this.executorService = new ThreadPoolExecutor(
                5,
                10,
                0L,
                TimeUnit.SECONDS,
                new java.util.concurrent.LinkedBlockingQueue<>(),
                new CustomThreadFactory()
        );
    }

    @PostConstruct
    public void init() {
        blockingQueue = redissonClient.getBlockingQueue("delay-queue");
        delayQueue = redissonClient.getDelayedQueue(blockingQueue);
        startConsume();
    }

    private void startConsume() {
        executorService.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String task = blockingQueue.take();
                    log.info("take task from delay queue: " + task);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } catch (Exception e) {
                    log.error("take task from delay queue error", e);
                }
            }
        });
    }

    public void addTask(String task, long delay) {
        delayQueue.offer(task, delay, TimeUnit.SECONDS);
        log.info("add task to delay queue: {} with delay: {}", task, delay);
    }

    private static class CustomThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, "DelayQueue-Thread");
            thread.setDaemon(true);
            return thread;
        }
    }
}
