package com.bamboo.imooc.a100110_synchronized;

/**
 * 类锁的第一种形式--(1)静态方法锁, 把synchronized加到静态方法上
 * Created by bamboo on 2019-07-18.
 */
public class ClassLockForStaticMethod03 implements Runnable {

    static ClassLockForStaticMethod03 instanceOne = new ClassLockForStaticMethod03();
    static ClassLockForStaticMethod03 instanceTwo = new ClassLockForStaticMethod03();


    @Override
    public void run() {
//        staticMethodNoneSynchronized();
        staticMethodWithSynchronized();
    }


    /**
     * 静态方法未使用锁(即非类锁)
     */
    public static void staticMethodNoneSynchronized() {
        System.out.println("我是静态方法未使用锁， 我叫:" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", 运行结束了...\n");
    }

    /**
     * 类锁的第一种形式：静态方法
     */
    public static synchronized void staticMethodWithSynchronized() {
        System.out.println("我是类锁的第一种形式：static Method(静态方法)， 我叫:" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", 运行结束了...\n");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instanceOne);
        Thread t2 = new Thread(instanceTwo);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("finish......");
    }

}
