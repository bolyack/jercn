package com.bamboo.core.threads.pools;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * Java 定时调度线程池
 *     创建和启动一个周期的线程，该线程第在初始delay时间后第一次启动，然后周期性的执行（周期：period）。
 *     如果一个线程运行的时间大于周期period,后面的线程可能会晚一点启动，但不会同时执行。
 *
 * @See  https://www.jianshu.com/p/0cfcea8db493
 * @See  https://www.jianshu.com/p/8c4c160ebdf7 (调度周期设置 的时间比任务本身执行的时间短的话会出现什么情况？也就是在线程调度时间已经到了但是上次的任务还没有做完的情况下，ScheduleExecutorService是怎么处理的？)
 *    一个任务会被重复添加到一个延时任务队列，所以同一时间任务队列中会有多个任务待调度，线程池会首先获取优先级高的任务执行。
 *    如果我们的【任务运行时间大于设置的调度时间】，那么效果就是[任务运行多长时间，调度时间就会变为多久]，
 *    因为添加到任务队列的任务的延时时间每次都是负数，所以会被立刻执行。
 */
public class Test_020_NewSingleThreadScheduledExecutor {

    private static Logger logger = Logger.getLogger(Test_020_NewSingleThreadScheduledExecutor.class.getName());

    public static void testNewSingleThreadScheduledExecutor() throws InterruptedException {
        ScheduledExecutorService schedules = Executors.newSingleThreadScheduledExecutor();
        // 1秒打印一次 当前线程名,
        // command: 进行调度的线程
        // initialDelay: 线程第一次执行的初始时延
        // period: 两个连续线程的周期(既线程的执行间隔)
        schedules.scheduleAtFixedRate(() -> logger.info("当前线程名：" + Thread.currentThread().getName()),
                3, 1, TimeUnit.SECONDS);
        // 主线程等待10秒
        TimeUnit.SECONDS.sleep(10);
        logger.info("---主线程退出了---");
    }

    public static void testThreadScheduledByFactory() throws InterruptedException {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread daemonThread = new Thread(r,"子线程--守护线程");
                // 设置线程为守护线程，主线程退出，子线程也随之退出
                daemonThread.setDaemon(true);
                return daemonThread;
            }
        });
        // 1秒打印一次 当前线程名
        service.scheduleAtFixedRate(()-> logger.info("当前线程名：" + Thread.currentThread().getName()),
                2, 1, TimeUnit.SECONDS);
        // 主线程等待5秒
        TimeUnit.SECONDS.sleep(5);
        logger.info("主线程退出了");
    }

    public static void main(String[] args) throws Exception {
//        testNewSingleThreadScheduledExecutor();
//        testThreadScheduledByFactory();
    }

}
