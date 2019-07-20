package com.bamboo.imooc.a100110_synchronized;

/**
 * 对象锁---包括方法锁(锁对象默认为this,即当前实例对象)
 * Created by bamboo on 2019-07-10.
 */
public class ObjectLockForMethod02 implements Runnable {

    static ObjectLockForMethod02 instance = new ObjectLockForMethod02();

    @Override
    public void run() {
        commonMethodDecorate();
    }

    /***
     * 对象锁的形式2---普通方法锁(默认锁对象是this，即当前对象实例)
     */
    public synchronized void commonMethodDecorate() {
        System.out.println("我是对象锁的方法修饰符形式，我叫 " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", 运行结束....\n");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while(t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("finish...");

    }

}
