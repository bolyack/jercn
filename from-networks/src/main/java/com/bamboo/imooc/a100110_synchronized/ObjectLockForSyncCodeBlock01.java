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
//        commonSynchronizedThis();
//        synchronizedAssignObejctLock();
//        synchronizedAppointMultDifferentLock();
        synchronizedAppointMultSameLock();
    }


    /**
     * Synchronized同步代码块使用this时
     */
    public void commonSynchronizedThis() {
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

        synchronized (lockOne) { //--指定锁对象: lockOne
            System.out.println("我是lockOne。我叫：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", lockOne部分运行结束。\n");
        }

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
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("finished......");
    }

}
