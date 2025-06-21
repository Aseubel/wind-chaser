package com.aseubel.algorithm.ratelimiter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Aseubel
 * @date 2025/6/21 下午3:18
 */
public class FixedWindowRateLimiter {
    private Integer QPS = 2;
    private long TIME_WINDOWS = 1000;
    private AtomicInteger REQ_COUNT = new AtomicInteger();
    private long START_TIME = System.currentTimeMillis();

    public FixedWindowRateLimiter(int qps, long timeWindows) {
        QPS = qps;
        TIME_WINDOWS = timeWindows;
    }

    public boolean tryAcquire() {
        synchronized (this) {
            if ((System.currentTimeMillis() - START_TIME) > TIME_WINDOWS) {
                REQ_COUNT.set(0);
                START_TIME = System.currentTimeMillis();
            }
            return REQ_COUNT.incrementAndGet() <= QPS;
        }
    }
}
