package com.bamboo.imooc.a100110_synchronized;

/**
 * 类锁的第二种形式--(2)代码块(Clazz.class), 代码块使用Clazz.class类类型加锁
 * Created by bamboo on 2019-07-19.
 */
public class ClassLockForCodeBlockClass04 implements Runnable {

    static ClassLockForCodeBlockClass04 instanceOne = new ClassLockForCodeBlockClass04();
    static ClassLockForCodeBlockClass04 instanceTwo = new ClassLockForCodeBlockClass04();

    @Override
    public void run() {
//        codeBlockWithNoneAndThis();
        codeBlockWithSynchronizedAndClazz();
    }

    /***
     * synchronized代码块使用类类型锁
     */
    public void codeBlockWithSynchronizedAndClazz() {
        synchronized (ClassLockForCodeBlockClass04.class) {
            System.out.println("我是类锁的第二种形式：synchronized(*.class)。我叫: " + Thread.currentThread().getName() );
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 运行结束了\n ");
        }
    }

    /**
     * 代码块使用了this锁(其实是对象锁)
     */
    public void codeBlockWithNoneAndThis() {
        synchronized (this) {
            System.out.println("我是类锁的第二种形式的反列：synchronized(this)。我叫: " + Thread.currentThread().getName() );
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 运行结束了\n ");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instanceOne);
        Thread t2 = new Thread(instanceTwo);
        t1.start();
        t2.start();
        while(t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("finish......");
    }

}
