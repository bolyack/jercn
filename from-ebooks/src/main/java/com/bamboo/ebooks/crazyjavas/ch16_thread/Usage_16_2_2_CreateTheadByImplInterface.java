package com.bamboo.ebooks.crazyjavas.ch16_thread;

/**
 * Created by bamboo on 2017/2/22.
 * 实现Runnable接口创建线程类
 *

 *
 */
public class Usage_16_2_2_CreateTheadByImplInterface {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                System.out.println("==========当j=20时开启两个线程==========");

                /**
                 * Runnable对象仅仅作为Thread对象的target，Runnable实现类里包含的run()方法仅作为线程执行体。而实际的线程对象依然是Thread实例，
                 * 只是该Thread线程负责执行其target的run()方法。
                 */
                //创建Runnable实现类的对象
                Usage_16_2_2_CreateByImplRunnable ut = new Usage_16_2_2_CreateByImplRunnable();
                //通过new Thread(target)方法创建新线程， 未指定线程名字，则线程使用默认的规则为当前线程命名
                new Thread(ut).start();
                Thread.currentThread().sleep(10l);
                //通过new Thread(target, name)方法创建新线程, 并指定线程的名字
                new Thread(ut, "新线程1").start();
            }
        }
    }

    /**
     * 从上面main()的运行结果来看，一般情况下两个子线程的i变量是连续的(不考虑多核机器高并发情况)， 也就是采用Runnable接口的方式
     * 创建多个线程可以共享线程类(target)的实例变量。这是因为在这种方式下，程序所创建的Runnable对象只是线程的target，而多个线程可共享
     * 同一个target，所以多个线程可以共享同一个线程类(实际上应该是线程的target类)的实例变量。
     */

}

/**
 * 实现Runnable接口创建线程类
 * 具体步骤如下：
 *  a.定义Runnable接口的实现类，并重写该接口的run()方法，该run()方法的方法体同样是该线程的执行体。
 *  b.创建Runnable实现类的实例，并以此实例作为Thread的target来创建Thread对象，该Thread对象才是真正的线程对象。
 *  c.调用线程对象Thread的start()方法来启动该线程。
 */
//通过实现Runnable接口来创建线程类
class Usage_16_2_2_CreateByImplRunnable implements Runnable {

    private int i = 0;

    /**
     * run()方法是线程执行体
     */
    public void run() {
        for (; i < 100; i++) {
            /**
             * 当线程类实现Runnable接口时，如果想获取当前线程对象，只能用Thread.currentThread()方法
             */
            System.out.println(Thread.currentThread().getName() + " => " + i);
        }
    }
}
