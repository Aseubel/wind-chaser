package com.aseubel.dynamictp.abortpolicy;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义实现拒绝策略
 *
 * @author Aseubel
 * @date 2025/9/14 上午9:21
 */
@Slf4j
public class AbortPolicyWithReport extends ThreadPoolExecutor.AbortPolicy {

    private final String threadName;

    public AbortPolicyWithReport(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        String msg = String.format(
                "Thread pool is EXHAUSTED!"
                        + " Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d),"
                        + " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)!",
                threadName, e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(),
                e.getLargestPoolSize(), e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(),
                e.isTerminating());
        log.warn(msg);
        throw new RejectedExecutionException(msg);
    }
}
