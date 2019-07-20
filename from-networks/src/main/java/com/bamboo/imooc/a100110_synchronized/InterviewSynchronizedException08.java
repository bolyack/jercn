package com.bamboo.imooc.a100110_synchronized;

/**
 *   面试题：7. 方法抛异常后，会释放锁
 *      结论：展示不抛出异常前和抛出异常后的对比
 *              一旦抛出了异常，第二个线程会立刻进入同步方法，意味着锁已经释放
 * Created by bamboo on 2019-07-20.
 */
public class InterviewSynchronizedException08 implements Runnable {

    static InterviewSynchronizedException08 instance = new InterviewSynchronizedException08();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
//            synchronizedMethodWithException();
            synchronizedMethodWithRuntimeException();
        } else {
            synchronizedMethodWithNone();
        }
    }

    /**
     * 模拟普通同步方法抛出异常
     *   有一个问题：虽然抛出异常了，该方法的前后打印语句都执行了，因为这里我们有try-catch操作。
     */
    public synchronized void synchronizedMethodWithException() {
        System.out.println("我是普通同步方法--抛出异常。我叫：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
            throw new Exception("顶顶顶异常"); //不需要手动释放锁，JVM会帮助你释放
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", 抛出异常 运行结束了\n");
    }


    public synchronized void synchronizedMethodWithRuntimeException() {
        System.out.println("我是普通同步方法--抛出运行时异常。我叫：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("顶顶顶异常"); //--未做任何try-catch处理，JVM仍然帮我们释放锁---
    }


    /**
     * 非静态加锁方法(实际使用实例锁)
     */
    public synchronized void synchronizedMethodWithNone() {
        System.out.println("我是普通同步方法--normalMethod。我叫：" + Thread.currentThread().getName());
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
