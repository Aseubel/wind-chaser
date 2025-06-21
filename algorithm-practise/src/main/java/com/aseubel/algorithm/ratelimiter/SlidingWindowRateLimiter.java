package com.aseubel.algorithm.ratelimiter;

/**
 * @author Aseubel
 * @date 2025/6/21 下午3:22
 */
public class SlidingWindowRateLimiter {
    private long windowSize;
    private long maxRequest;
    private long currentRequest;
    private long lastTime;

    public SlidingWindowRateLimiter(long windowNumber, long maxRequest) {
        this.windowSize = 1000 / windowNumber;
        this.maxRequest = maxRequest;
        this.currentRequest = 0;
        this.lastTime = System.currentTimeMillis();
    }

    public boolean tryAcquire() {
        synchronized (this) {
            long now = System.currentTimeMillis();
            long elapsedTime = now - lastTime;
            if (elapsedTime >= windowSize) {
                currentRequest = 0;
            }
            if (currentRequest >= maxRequest) {
                return false;
            }
            currentRequest++;
            lastTime = now;
            return true;
        }
    }
}
