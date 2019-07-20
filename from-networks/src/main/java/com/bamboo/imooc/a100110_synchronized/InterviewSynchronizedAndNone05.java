package com.bamboo.imooc.a100110_synchronized;

/**
 * 面试题：  4. 同时访问同步方法与非同步(synchronized)方法
 *    结论： 非同步方法不受影响
 * Created by bamboo on 2019-07-20.
 */
public class InterviewSynchronizedAndNone05 implements Runnable {

    static InterviewSynchronizedAndNone05 instance = new InterviewSynchronizedAndNone05();

    @Override
    public void run() {
        //---因启动线程未指定线程名，故线程名默认从Thread-0开始---
        //---这样写就相当于模拟，methodWithSync()与methodWithNone()同时执行
        if (Thread.currentThread().getName().equals("Thread-0")) {
            methodWithSync();
        } else {
            methodWithNone();
        }
    }

    public synchronized void methodWithSync() {
        System.out.println("我是加锁的方法。我叫：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", 加锁 运行结束了\n");
    }

    public void methodWithNone() {
        System.out.println("我是没加锁的方法。我叫：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", 没加锁 运行结束了\n");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("finish....");
    }


}
