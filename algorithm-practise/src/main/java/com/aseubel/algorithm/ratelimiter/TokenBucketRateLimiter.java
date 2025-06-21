package com.aseubel.algorithm.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.TimeUnit;

/**
 * 一个基于 Google Guava RateLimiter 实现的令牌桶限流器。
 * <p>
 * 这个类是线程安全的。
 */
public class TokenBucketRateLimiter {

    private final RateLimiter rateLimiter;
    private final String resource;

    /**
     * 创建一个令牌桶限流器。
     *
     * @param permitsPerSecond 每秒生成的令牌数（QPS）。
     * @param resource         受保护的资源标识，用于日志或监控。
     */
    public TokenBucketRateLimiter(double permitsPerSecond, String resource) {
        if (permitsPerSecond <= 0) {
            throw new IllegalArgumentException("permitsPerSecond 必须为正数");
        }
        this.rateLimiter = RateLimiter.create(permitsPerSecond);
        this.resource = resource;
    }

    /**
     * 创建一个带有预热功能的令牌桶限流器。
     * <p>
     * 在预热期间，令牌生成速率会逐渐增加，直到达到稳定速率。这有助于防止系统在冷启动时被突发流量压垮。
     *
     * @param permitsPerSecond 每秒生成的令牌数（QPS）。
     * @param warmupPeriod     从冷启动到达到稳定速率所需的预热时间。
     * @param unit             预热时间单位。
     * @param resource         受保护的资源标识。
     */
    public TokenBucketRateLimiter(double permitsPerSecond, long warmupPeriod, TimeUnit unit, String resource) {
        if (permitsPerSecond <= 0) {
            throw new IllegalArgumentException("permitsPerSecond 必须为正数");
        }
        this.rateLimiter = RateLimiter.create(permitsPerSecond, warmupPeriod, unit);
        this.resource = resource;
    }

    /**
     * 尝试获取一个令牌。
     * <p>
     * 这是一个非阻塞方法。如果令牌桶中有可用的令牌，则立即返回 true；否则立即返回 false。
     *
     * @return 如果获取成功返回 true，否则返回 false。
     */
    public boolean tryAcquire() {
        boolean acquired = rateLimiter.tryAcquire();
        if (acquired) {
            System.out.printf("[%s] - %s: 成功获取令牌。\n", Thread.currentThread().getName(), resource);
        } else {
            System.out.printf("[%s] - %s: 获取令牌失败，请求被限流。\n", Thread.currentThread().getName(), resource);
        }
        return acquired;
    }

    /**
     * 获取一个令牌，如果必要则阻塞等待。
     * <p>
     * 此方法会阻塞当前线程，直到获取到令牌为止。返回值为线程的等待时间（秒）。
     *
     * @return 线程为获取令牌所花费的等待时间（秒）。
     */
    public double acquire() {
        double waitTimeInSeconds = rateLimiter.acquire();
        System.out.printf("[%s] - %s: 成功获取令牌，等待时间: %.2f 秒。\n", Thread.currentThread().getName(), resource, waitTimeInSeconds);
        return waitTimeInSeconds;
    }

    /**
     * 获取指定数量的令牌，如果必要则阻塞等待。
     *
     * @param permits 要获取的令牌数量。
     * @return 线程为获取令牌所花费的等待时间（秒）。
     */
    public double acquire(int permits) {
        if (permits <= 0) {
            throw new IllegalArgumentException("要获取的令牌数必须为正数");
        }
        double waitTimeInSeconds = rateLimiter.acquire(permits);
        System.out.printf("[%s] - %s: 成功获取 %d 个令牌，等待时间: %.2f 秒。\n", Thread.currentThread().getName(), resource, permits, waitTimeInSeconds);
        return waitTimeInSeconds;
    }
}