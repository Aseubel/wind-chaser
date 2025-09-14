package com.aseubel.dynamictp.service.impl;

import com.aseubel.dynamictp.service.TestService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.dynamictp.core.DtpRegistry;
import org.dromara.dynamictp.core.executor.DtpExecutor;
import org.dromara.dynamictp.core.executor.OrderedDtpExecutor;
import org.dromara.dynamictp.core.support.task.runnable.NamedRunnable;
import org.dromara.dynamictp.core.support.task.runnable.OrderedRunnable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Aseubel
 * @date 2025/9/13 下午11:56
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    private final ThreadPoolExecutor jucThreadPoolExecutor;

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private final DtpExecutor eagerDtpExecutor;

    private final ScheduledExecutorService scheduledDtpExecutor;

    private final OrderedDtpExecutor orderedDtpExecutor;

    public TestServiceImpl(@Qualifier("jucThreadPoolExecutor") ThreadPoolExecutor jucThreadPoolExecutor,
                           ThreadPoolTaskExecutor threadPoolTaskExecutor,
                           @Qualifier("eagerDtpExecutor") DtpExecutor eagerDtpExecutor,
                           @Qualifier("scheduledDtpExecutor") ScheduledExecutorService scheduledDtpExecutor,
                           @Qualifier("orderedDtpExecutor") OrderedDtpExecutor orderedDtpExecutor) {
        this.jucThreadPoolExecutor = jucThreadPoolExecutor;
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.eagerDtpExecutor = eagerDtpExecutor;
        this.scheduledDtpExecutor = scheduledDtpExecutor;
        this.orderedDtpExecutor = orderedDtpExecutor;
    }

    @Override
    public void testJucTp() {
        for (int i = 0; i < 10; i++) {
            jucThreadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                log.info("i am a jucThreadPoolExecutor's task");
            });
        }
    }

    @Override
    public void testSpringTp() {
        for (int i = 0; i < 10; i++) {
            threadPoolTaskExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                log.info("i am a threadPoolTaskExecutor's task");
            });
        }
    }

    @Override
    public void testCommonDtp() {
        Executor dtpExecutor1 = DtpRegistry.getExecutor("dtpExecutor1");
        for (int i = 0; i < 10; i++) {
            dtpExecutor1.execute(NamedRunnable.of(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                log.info("i am a dtpExecutor's task");
            }, "task" + i));
        }
    }

    @Override
    public void testEagerDtp() {
        for (int i = 0; i < 10; i++) {
            eagerDtpExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                log.info("i am a eagerDtpExecutor's task");
            });
        }
    }

    @Override
    public void testScheduledDtp() {
        scheduledDtpExecutor.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.info("i am a scheduledDtpExecutor's task");
        }, 0, 2, TimeUnit.SECONDS);
    }

    @Override
    public void testOrderedDtp() {
        for (int i = 0; i < 10; i++) {
            orderedDtpExecutor.execute(new TestOrderedRunnable(new UserInfo(i, "dtp" + i)));
        }
    }

    public static class TestOrderedRunnable implements OrderedRunnable {

        private final UserInfo userInfo;

        public TestOrderedRunnable(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        @Override
        public Object hashKey() {
            return userInfo.getUid();
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.info("i am a orderedDtpExecutor's task, userInfo: {}", userInfo);
        }
    }

    @Data
    @AllArgsConstructor
    public static class UserInfo {
        private long uid;
        private String name;
    }
}
