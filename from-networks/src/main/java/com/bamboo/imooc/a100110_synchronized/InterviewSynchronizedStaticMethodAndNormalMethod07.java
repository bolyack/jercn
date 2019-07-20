package com.bamboo.imooc.a100110_synchronized;

/**
 * 面试题：  6. 同时访问静态synchronized和非静态synchronized方法
 *      结论：并发同时执行，锁不生效，不会干扰
 * Created by bamboo on 2019-07-20.
 */
public class InterviewSynchronizedStaticMethodAndNormalMethod07 implements Runnable {

    static InterviewSynchronizedStaticMethodAndNormalMethod07 instance = new InterviewSynchronizedStaticMethodAndNormalMethod07();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            synchronizedMethodWithStatic();
        } else {
            synchronizedMethodWithNone();
        }
    }

    /**
     * 静态同步方法(实际使用类锁)
     */
    public synchronized static void synchronizedMethodWithStatic() {
        System.out.println("我是静态加锁的同步方法--staticMethod。我叫：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", staticMethod 运行结束了\n");
    }

    /**
     * 非静态加锁方法(实际使用实例锁)
     */
    public synchronized void synchronizedMethodWithNone() {
        System.out.println("我是非静态加锁方法--normalMethod。我叫：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", normalMethod 运行结束了\n");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while(t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("finish....");
    }

}
