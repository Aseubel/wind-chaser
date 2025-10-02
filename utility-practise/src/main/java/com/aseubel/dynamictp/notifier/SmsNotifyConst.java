package com.aseubel.dynamictp.notifier;

/**
 * @author Aseubel
 * @date 2025/9/14 上午12:01
 */
public class SmsNotifyConst {

    private SmsNotifyConst() { }

    public static final String SMS_ALARM_TEMPLATE =
            """
                    服务名称：%s
                    实例信息：%s
                    环境：%s
                    线程池名称：%s
                    报警项：%s
                    报警阈值 / 当前值：%s
                    核心线程数：%s
                    最大线程数：%s
                    当前线程数：%s
                    活跃线程数：%s
                    历史最大线程数：%s
                    任务总数：%s
                    执行完成任务数：%s
                    等待执行任务数：%s
                    队列类型：%s
                    队列容量：%s
                    队列任务数量：%s
                    队列剩余容量：%s
                    拒绝策略：%s
                    总拒绝任务数量：%s
                    总执行超时任务数量：%s
                    总等待超时任务数量：%s
                    上次报警时间：%s
                    报警时间：%s
                    接收人：@%s
                    统计周期：%ss
                    静默时长：%ss
                    trace 信息：%s
                    扩展信息：%s
                    """;

    public static final String SMS_NOTICE_TEMPLATE =
            """
                    服务名称：%s
                    实例信息：%s
                    环境：%s
                    线程池名称：%s
                    核心线程数：%s => %s
                    最大线程数：%s => %s
                    允许核心线程超时：%s => %s
                    线程存活时间：%ss => %ss
                    队列类型：%s
                    队列容量：%s => %s
                    拒绝策略：%s => %s
                    接收人：@%s
                    通知时间：%s
                    """;
}
