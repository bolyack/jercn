package com.bamboo.core.juc.aqs;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: jercn
 * @description: 线程join方法用例
 * @author: bamboo
 * @create: 2021-04-08
 * @link https://mp.weixin.qq.com/s/72XXWIMJ230bwfu4PUuS-Q
 **/
public class CountDownLatchExample {

    static CountDownLatch latch = new CountDownLatch(2);

    /**
     * 多个线程与主线程 多次执行输出的顺序不一样
     */
    public static void common () {
        //创建线程1
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("这是线程1");
            }
        });
        t1.start();

        //创建线程2
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("这是线程2");
        });
        t2.start();

        //主线程
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("这是主线程");
    }

    /**
     * 使用join
     * @throws InterruptedException
     */
    public static void usageJoin() throws InterruptedException {
        //创建线程1
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("这是线程1");
                latch.countDown();
            }
        });
        t1.start();

        //创建线程2
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("这是线程2");
        });
        t2.start();

        // 等待线程 1和线程 2 执行完
        t1.join();
        t2.join();

        //主线程
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("这是主线程");
    }


    /**
     * 为了实现等待所有线程池执行完之后再执行主线程的逻辑，
     * 使用 AQS（AbstractQueuedSynchronizer，抽象同步框架）下的著名类 CountDownLatch 来实现此功能
     * @throws InterruptedException
     */
    public static void usagePool() throws InterruptedException {
        //TODO 不推荐使用，这里为了演示方便
        ExecutorService es = Executors.newFixedThreadPool(2);

        //任务一
        es.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("这是线程1");
                latch.countDown();
            }
        });

        //任务二
        es.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("这是线程2");
                latch.countDown();
            }
        });

        // 等待任务执行完成
        latch.await();

        //主线程
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("这是主线程");
        es.shutdown();
    }


    public static void main(String[] args) throws InterruptedException {
//        common();
//        usageJoin();
        usagePool();
    }

}
