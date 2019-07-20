package com.bamboo.imooc.a100110_synchronized;

/**
 *   面试题：5. 访问同一个类的不同的普通同步(synchronized)方法
 *      结论：顺序执行，锁生效
 * Created by bamboo on 2019-07-20.
 */
public class InterviewDifferentSynchronizedMethod06 implements Runnable {

    static InterviewDifferentSynchronizedMethod06 instance = new InterviewDifferentSynchronizedMethod06();
    static InterviewDifferentSynchronizedMethod06 instanceOther = new InterviewDifferentSynchronizedMethod06();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            methodWithSyncOne();
        } else {
            methodWithSyncTwo();
        }
    }

    /**
     * 这里synchronized锁的是this，也即锁是当前的实例----同一个实例锁
     */
    public synchronized void methodWithSyncOne() {
        System.out.println("我是加锁的同步方法一。我叫：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", 加锁同步方法一 运行结束了\n");
    }

    /**
     * 这里synchronized锁的是this，也即锁是当前的实例----同一个实例锁
     */
    public synchronized void methodWithSyncTwo() {
        System.out.println("我是加锁的同步方法二。我叫：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", 加锁同步方法二 运行结束了\n");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
//        Thread t2 = new Thread(instanceOther);
        t1.start();
        t2.start();
        while(t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("finish....");
    }

}
