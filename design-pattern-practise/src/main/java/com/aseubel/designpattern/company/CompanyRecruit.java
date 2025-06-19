package com.aseubel.designpattern.company;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static com.aseubel.designpattern.company.CompanyRecruit.Status.*;

/**
 * @author Aseubel
 * @date 2025/6/18 下午4:22
 * @description
 */
@Slf4j
public class CompanyRecruit {
    // 招聘名称
    private String name;
    // 招聘职位
    private Major position;
    // 招聘状态
    private volatile Status status = NO_START;
    // 简历列表
    private final BlockingDeque<AbstractDeveloper> resumes;
    // 收到的简历数量
    private final AtomicInteger resumeSize;
    // 参与者
    private final BlockingDeque<AbstractDeveloper> candidates;
    // 可以面试的人数
    private final AtomicInteger candidateSize;
    // 面试官
    private Consumer<AbstractDeveloper> consumer;
    // 当前面试者
    private AbstractDeveloper currentCandidate;
    // 面试官线程
    private Thread consumeThread;

    public CompanyRecruit() {
        this.candidates = new LinkedBlockingDeque<>();
        this.resumes = new LinkedBlockingDeque<>();
        this.resumeSize = new AtomicInteger(0);
        this.candidateSize = new AtomicInteger(0);
        setConsumeThread();
    }

    public synchronized void start(String name, Major position) {
        if (status != NO_START) {
            throw new IllegalStateException("招聘已经开始或已结束，请勿重复操作！");
        }
        log.info("线程 {} 开始招聘 {}, 职位: {}", Thread.currentThread().getName(), name, position.getDisplayName());
        this.status = NEW_START;
        setBasic(name, position);
    }

    public synchronized void start(Consumer<AbstractDeveloper> consumer, String name, Major position) {
        if (status != NO_START) {
            throw new IllegalStateException("招聘已经开始或已结束，请勿重复操作！");
        }
        log.info("线程 {} 开始招聘 {}, 职位: {}", Thread.currentThread().getName(), name, position.getDisplayName());
        this.status = WAITING_FOR_RESUME;
        this.consumer = consumer;
        setBasic(name, position);
    }

    private void setBasic(String name, Major position) {
        this.name = name;
        this.position = position;
    }

    public void setConsumer(Consumer<AbstractDeveloper> consumer) {
        if (status == NO_START) {
            throw new IllegalStateException("请先开启招聘流程！");
        }
        if (status == NEW_START) {
            synchronized (this) {
                this.status = WAITING_FOR_RESUME;
                log.info("线程 {} 修改了面试官", Thread.currentThread().getName());
                this.consumer = consumer;
            }
        } else {
            throw new IllegalStateException("当前状态不可修改面试官！");
        }
    }

    private void setConsumeThread() {
        consumeThread = new Thread(() -> {
            try {
                do {
                    AbstractDeveloper developer = candidates.take();
                    log.info("开始面试 {}", developer.getName());
                    // sleep随机时间模拟面试时间
                    Thread.sleep((long) (Math.random() * 3000));
                    consumer.accept(developer);
                    log.info("{} 面试结束", developer.getName());
                } while (!shouldStopConsume());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("面试官进程强制中断！");
            }
        });
    }

    public boolean receiveResume(AbstractDeveloper developer) {
        if (ObjectUtil.isEmpty(developer) || !canGetResume()) {
            return false;
        }
        if (resumes.offer(developer)) {
            resumeSize.incrementAndGet();
            boolean added = addToCandidates(developer);
            log.info("简历接收：{}，符合条件：{}", developer.getName(), added);
            return added;
        }
        return false;
    }

    private boolean addToCandidates(AbstractDeveloper developer) {
        if (developer.getMajor() == this.position && rightAge(developer)) {
            if (candidates.offer(developer)) {
                candidateSize.incrementAndGet();
                return true;
            }
        }
        return false;
    }

    private boolean rightAge(AbstractDeveloper developer) {
        return developer.getAge() >= 18 && developer.getAge() <= 35;
    }

    // 开始面试
    public synchronized void consume() {
        if (status != WAITING_FOR_RESUME || consumeThread.getState() != Thread.State.NEW) {
            throw new IllegalStateException("流程错误！");
        }
        log.info("面试开始！！！");
        status = CONSUMING;
        consumeThread.start();
    }

    // 结束招聘，面试正常结束
    public void over() {
        status = ONLY_CONSUMING;
        log.info("招聘结束！！！等待面试结束......");
    }

    // 强制中断招聘和面试
    public void stop() {
        log.warn("强制停止招聘和现有面试！！");
        consumeThread.interrupt();
        status = END;
    }

    public String recruitOverView() {
        return "招聘名称：" + (StrUtil.isNotEmpty(name)?name:"未设置")
                + "  招聘职位：" + (ObjectUtil.isNotEmpty(position)?position.getDisplayName():"未设置")
                + "  收到简历数量：" + resumeSize.get()
                + "  进入面试者数量：" + candidateSize.get()
                + "  招聘状态：" + status.name();
    }

    private boolean shouldStopConsume() {
        return status == END || status == ONLY_CONSUMING && candidates.isEmpty();
    }

    private boolean canGetResume() {
        return status != NO_START && status != ONLY_CONSUMING && status != END;
    }

    @Getter
    public enum Status {
        NO_START(0), // 未开启
        NEW_START(1), // 刚开始
        WAITING_FOR_RESUME(2), // 等待参与者
        CONSUMING(3), // 面试中
        ONLY_CONSUMING(4), // 仅面试，停止简历投递
        END(5), // 此次招聘结束
        ;

        private final int statusCode;

        Status(int statusCode) {
            this.statusCode = statusCode;
        }

    }
}
