package com.bamboo.core.juc.aqs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: jercn
 * @description: Semaphore usage demo
 * @author: xdj
 * @create: 2021-04-22
 **/
public class SemaphoreExample {

    //创建信号量
    static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {

        //创建 5 个固定线程池，方便演示用默认创建方式
        ExecutorService es = Executors.newFixedThreadPool(5);

        //定义执行任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //拿到当前线程的名称
                System.out.println(String.format("老司机：%s, 停车场外排队，时间：%s",
                        Thread.currentThread().getName(),
                        DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())));
                try {
                    //执行此行，让所有线程先排队等待进入停车场
                    Thread.sleep(100);

                    // 执行阻塞
                    semaphore.acquire();

                    System.out.println(String.format("老司机：%s, 已进入停车场，时间：%s",
                            Thread.currentThread().getName(),
                            DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())));

                    Thread.sleep(1000L);


                    System.out.println(String.format("老司机：%s, 离开停车场，时间：%s",
                            Thread.currentThread().getName(),
                            DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())));

                    //释放锁
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        //执行任务1
        es.submit(runnable);
        //执行任务2
        es.submit(runnable);
        //执行任务3
        es.submit(runnable);
        //执行任务4
        es.submit(runnable);
        //执行任务5
        es.submit(runnable);

        // 等线程池任务执行完之后关闭
        es.shutdown();
    }

}
