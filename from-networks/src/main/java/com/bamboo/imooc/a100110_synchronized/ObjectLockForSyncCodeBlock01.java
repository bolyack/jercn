package com.bamboo.imooc.a100110_synchronized;

/**
 * 对象锁---同步代码块锁(自己指定锁对象)
 * Created by bamboo on 2019-07-10.
 */
public class ObjectLockForSyncCodeBlock01 implements Runnable {

    static ObjectLockForSyncCodeBlock01 instance = new ObjectLockForSyncCodeBlock01();

    Object lockOne = new Object();//充当锁对象(假定锁1)
    Object lockTwo = new Object();//充当锁对象(假定锁2)

    @Override
    public void run() {
        commonSynchronizedThis();
//        synchronizedAssignObejctLock();
//        synchronizedAppointMultDifferentLock();
//        synchronizedAppointMultSameLock();
    }


    /**
     * Synchronized同步代码块使用this时
     */
    public void commonSynchronizedThis() {
        //---用synchronized---保护这个代码块内容--串行执行----
        synchronized (this) {
            System.out.println("我是对象锁的代码块形式。我叫 ：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 运行结束了\n");
        }
    }

    /***
     * Synchronized指定锁对象的
     */
    public void synchronizedAssignObejctLock() {
        synchronized (lockOne) {
            System.out.println("我是对象锁的代码块形式。我叫 ：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 运行结束了\n");
        }
    }

    /**
     * Synchronized根据业务指定不同锁对象(操作不同业务时)
     */
    public void synchronizedAppointMultDifferentLock() {
        //---两个代码块--保护的时机不同，做不同业务处理-----
        synchronized (lockOne) { //--指定锁对象: lockOne
            System.out.println("我是lockOne。我叫：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", lockOne部分运行结束。\n");
        }

        //------其他业务处理--------

        synchronized (lockTwo) { //--指定锁对象: lockTwo
            System.out.println("我是lockTwo。我叫：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", lockTwo部分运行结束。\n");
        }

    }

    /**
     * Synchronized根据业务指定相同锁对象(操作不同业务时)
     */
    public void synchronizedAppointMultSameLock() {
        synchronized (lockOne) { //--指定相同锁对象: lockOne
            System.out.println("我是lockOne。我叫：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", lockOne部分运行结束。\n");
        }

        synchronized (lockOne) { //--指定相同锁对象: lockOne
            System.out.println("我是lockTwo。我叫：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", lockTwo部分运行结束。\n");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        //---只要两个线程有一个存在，则继续循环---
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("finished......");
    }

}
