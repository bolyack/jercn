package com.bamboo.imooc.a100110_synchronized;

/**
 * 消失的请求----引发线程同步问题
 * Created by bamboo on 2019-07-19.
 */
public class DisappearRequestRorSynchronizedStart00 implements Runnable {

    static DisappearRequestRorSynchronizedStart00 instance = new DisappearRequestRorSynchronizedStart00();
    static int i = 0;

    @Override
    public void run() {
//        noneSynchronizedTrouble();
//        solveForObjectLockWhithCommonMethdAddSynchronized();
//        solveForObjectLockWithCodeBlockAddSynchronized();
//        solveClassLockForStaticMethodAddSynchronized();
        solveClassLockForCodeBlockWithClazz();
    }

    /***
     * 未使用同步锁带来的问题
     * 理论上，线程1执行10w次操作； 线程2执行10w次操作。运行结束后i的值应该是20w
     * 实际上，多运行几次，每次结果都不一样，和理论值相差很大。
     */
    public void noneSynchronizedTrouble() {
        for (int j = 0; j < 100000; j++) {
            i++;
        }
    }

    /**
     * 解决方案1---对象锁--在普通方法上使用synchronized关键字
     */
    public synchronized void solveForObjectLockWhithCommonMethdAddSynchronized() {
        for (int j = 0; j < 100000; j++) {
            i++;
        }
    }

    /**
     * 解决方案2---对象锁--在代码块上使用synchronized关键字同时指定this或者指定锁对象
     */
    public void solveForObjectLockWithCodeBlockAddSynchronized() {
        synchronized (this) {
            for (int j = 0; j < 100000; j++) {
                i++;
            }
        }
    }

    /**
     * 解决方案3---类锁--在静态方法上使用synchronized关键字
     */
    public static synchronized void solveClassLockForStaticMethodAddSynchronized() {
        for (int j = 0; j < 100000; j++) {
            i++;
        }
    }

    /**
     * 解决方案4---类锁--在代码块上使用synchronized同时指定Class类类型对象为锁对象
     */
    public void solveClassLockForCodeBlockWithClazz() {
        synchronized(DisappearRequestRorSynchronizedStart00.class) {
            for (int j = 0; j < 100000; j++) {
                i++;
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        System.out.println();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}
