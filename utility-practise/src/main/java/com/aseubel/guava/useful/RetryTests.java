package com.aseubel.guava.useful;

import com.github.rholder.retry.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Aseubel
 * @date 2025/10/4 下午8:41
 */
public class RetryTests {
    public static void main(String[] args) {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(result -> !result)  // 返回false就重试
                .retryIfExceptionOfType(IOException.class) // IO异常重试
                .withStopStrategy(StopStrategies.stopAfterAttempt(3)) // 最多3次
                .withWaitStrategy(WaitStrategies.fixedWait(500, TimeUnit.MILLISECONDS)) // 每次等待500毫秒
                .withBlockStrategy(BlockStrategies.threadSleepStrategy()) // 线程休眠策略
                .build();

        // 执行
        try {
            Boolean result = retryer.call(() -> {
                System.out.println("正在调用接口...");
                return remoteMethod();  // 返回 true/false
            });
        } catch (ExecutionException e) {
            System.out.println("调用失败：" + e.getCause());
        } catch (RetryException e) {
            System.out.println("重试失败：" + e.getLastFailedAttempt().getAttemptNumber()
                    + "，从第一次尝试到现在的时间：" + e.getLastFailedAttempt().getDelaySinceFirstAttempt());
        }
    }

    private static Boolean remoteMethod() {
        // 远程调用
        return false;
    }
}
