package com.aseubel.guava.useful;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @author Aseubel
 * @date 2025/10/4 下午8:06
 */
public class StopWatchTests {
    public static void main(String[] args) {
        // 启动计时器
        Stopwatch stopwatch = Stopwatch.createStarted();
        // 模拟业务操作
        try {
            Thread.sleep(300); // 让线程睡300毫秒
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 停止计时
        stopwatch.stop();
        // 输出耗时（单位毫秒）
        System.out.println("接口执行耗时：" + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
    }
}
