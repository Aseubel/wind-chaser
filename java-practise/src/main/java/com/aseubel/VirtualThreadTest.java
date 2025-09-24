package com.aseubel;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author Aseubel
 * @date 2025/9/24 下午4:55
 */
public class VirtualThreadTest {
    public static void main(String[] args) {
        Set<String> threadNames = ConcurrentHashMap.newKeySet();

        // 使用Executors创建虚拟线程
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        // 创建100万个虚拟线程
        IntStream.range(0, 1000000).forEach(i -> {
            executor.submit(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                String threadInfo = Thread.currentThread().toString(); // VirtualThread[#22]/runnable@ForkJoinPool-1-worker-1
                String workerName = threadInfo.split("@")[1];
                threadNames.add(workerName);
                return i;
            });
        });
        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 结果是12，不同电脑结果不同
        System.out.println("Thread names: " + threadNames.size());
    }
}
