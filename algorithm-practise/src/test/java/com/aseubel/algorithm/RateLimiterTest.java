package com.aseubel.algorithm;

import com.aseubel.algorithm.ratelimiter.FixedWindowRateLimiter;
import com.aseubel.algorithm.ratelimiter.LeakyBucketRateLimiter;
import com.aseubel.algorithm.ratelimiter.SlidingWindowRateLimiter;
import com.aseubel.algorithm.ratelimiter.TokenBucketRateLimiter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aseubel
 * @date 2025/6/21 下午3:30
 */
@SpringBootTest
public class RateLimiterTest {

    @Test
    public void fixedWindowTest() {
        FixedWindowRateLimiter fixedWindow = new FixedWindowRateLimiter(50, 1000);
        for (int i = 0; i < 100; i++) {
            // 模拟添加数据包
            if (!fixedWindow.tryAcquire()) {
                System.out.println("数据包 " + i + " 被丢弃");
            } else {
                System.out.println("数据包 " + i + " 被添加");
            }
            // 模拟数据包到达的间隔时间
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("数据包全部添加完毕");
    }

    @Test
    public void slidingWindowTest() {
        SlidingWindowRateLimiter rateLimiter = new SlidingWindowRateLimiter(20, 100);
        for (int i = 0; i < 100; i++) {
            // 模拟添加数据包
            if (!rateLimiter.tryAcquire()) {
                System.out.println("数据包 " + i + " 被丢弃");
            } else {
                System.out.println("数据包 " + i + " 被添加");
            }
            // 模拟数据包到达的间隔时间
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("数据包全部添加完毕");
    }

    @Test
    public void leakyBucketTest() {
        LeakyBucketRateLimiter leakyBucket = new LeakyBucketRateLimiter();
        for (int i = 0; i < 200; i++) {
            // 模拟添加数据包
            leakyBucket.addPacket(i);
            System.out.println("数据包 " + i + " 被添加");
            // 模拟数据包到达的间隔时间
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("数据包全部添加完毕");
    }

    // 测试 TokenBucketRateLimiter

    @Test
    @DisplayName("测试 tryAcquire：QPS=2，立即请求3次应只有2次成功")
    void testTryAcquire_ShouldLimitRequests() {
        // Arrange: 创建一个每秒生成2个令牌的限流器
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(2.0, "API-A");

        // Act & Assert:
        // 立即连续尝试获取3次
        assertTrue(limiter.tryAcquire(), "第一次请求应该立即成功");
        assertTrue(limiter.tryAcquire(), "第二次请求应该立即成功");
        assertFalse(limiter.tryAcquire(), "第三次请求应该因为速率限制而失败");
    }

    @Test
    @DisplayName("测试 tryAcquire：等待令牌重新填充后请求成功")
    void testTryAcquire_ShouldSucceedAfterRefill() throws InterruptedException {
        // Arrange: 创建一个每秒生成1个令牌的限流器
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(1.0, "API-B");

        // Act & Assert:
        assertTrue(limiter.tryAcquire(), "第一次请求应该成功");
        assertFalse(limiter.tryAcquire(), "紧接着的第二次请求应该失败");

        // 等待超过1秒，让令牌桶重新填充
        System.out.println("等待1.1秒让令牌桶填充...");
        TimeUnit.MILLISECONDS.sleep(1100);

        assertTrue(limiter.tryAcquire(), "等待后，新的请求应该成功");
    }

    @Test
    @DisplayName("测试 acquire 方法的阻塞行为")
    void testAcquire_ShouldBlockUntilTokenAvailable() {
        // Arrange: 创建一个每秒生成5个令牌的限流器 (每200ms一个)
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(5.0, "API-C");
        long startTime = System.currentTimeMillis();

        // Act: 连续获取5个令牌，这应该几乎是瞬时的
        for (int i = 0; i < 5; i++) {
            limiter.acquire();
        }
        long timeAfter5Acquires = System.currentTimeMillis();
        assertTrue((timeAfter5Acquires - startTime) < 100, "前5次获取应该非常快");

        // 再次获取一个令牌，这次应该会阻塞大约200ms
        limiter.acquire();
        long timeAfter6thAcquire = System.currentTimeMillis();

        // Assert: 验证总时间
        long totalDuration = timeAfter6thAcquire - startTime;
        System.out.printf("获取6个令牌总耗时: %d ms\n", totalDuration);
        assertTrue(totalDuration >= 200, "获取第6个令牌应该阻塞，总耗时应至少200ms");
        assertTrue(totalDuration < 400, "获取第6个令牌的阻塞时间不应过长");
    }

    @Test
    @DisplayName("测试多线程环境下的限流")
    void testRateLimiterInMultiThreadedEnv() throws InterruptedException {
        // Arrange: 创建一个每秒生成10个令牌的限流器
        final TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(10.0, "API-D-MultiThreaded");
        final java.util.concurrent.atomic.AtomicInteger successCount = new java.util.concurrent.atomic.AtomicInteger(0);

        // Act: 创建20个线程，每个线程尝试获取一次令牌
        Runnable task = () -> {
            if (limiter.tryAcquire()) {
                successCount.incrementAndGet();
            }
        };

        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        // Assert:
        // 由于所有线程几乎同时启动，成功的数量应该约等于初始桶中的令牌数（这里是10）
        // Guava的RateLimiter有"突发"特性，初始时桶是满的
        System.out.printf("多线程测试中，成功获取令牌的次数: %d\n", successCount.get());
        assertEquals(10, successCount.get(), "成功获取令牌的线程数应该等于QPS");
    }

    @Test
    @DisplayName("测试带有预热功能的限流器")
    void testWarmupRateLimiter() {
        // Arrange: 创建一个QPS为2，预热期为5秒的限流器
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(2.0, 5, TimeUnit.SECONDS, "API-E-Warmup");

        // Act & Assert:
        // 第一次获取5个令牌，由于预热，等待时间会比稳定期长
        double initialWaitTime = limiter.acquire(5);
        System.out.printf("预热期首次获取5个令牌等待时间: %.2f 秒\n", initialWaitTime);
        // 稳定期速率是0.5s/令牌, 5个需要2.5s。预热期会更长。
        assertTrue(initialWaitTime > 2.5, "预热期的等待时间应比稳定期长");

        // 再次获取5个令牌，此时速率应该已经接近稳定期
        double subsequentWaitTime = limiter.acquire(5);
        System.out.printf("预热期后续获取5个令牌等待时间: %.2f 秒\n", subsequentWaitTime);
        assertTrue(subsequentWaitTime > 2.4 && subsequentWaitTime < 2.6, "后续的等待时间应该接近稳定速率 (2.5秒)");
    }
}
