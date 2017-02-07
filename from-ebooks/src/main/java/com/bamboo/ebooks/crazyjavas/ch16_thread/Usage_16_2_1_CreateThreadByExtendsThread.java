package com.bamboo.ebooks.crazyjavas.ch16_thread;

/**
 * Created by bamboo on 2017/2/7.
 * 继续Thread类创建线程类
 */
public class Usage_16_2_1_CreateThreadByExtendsThread extends Thread { //通过继承Thread类来创建线程类

    private int i;

    //重写run()方法，run()方法的方法体就是线程执行体
    public void run() {
        for (; i < 100; i++) {
            //当线程类继承Thread类时，直接使用this即可获取当前线程Thread对象
            //当前线程Thread对象的getName()返回当前线程的名字
            //因此可以(省略this)直接调用getName()方法返回当前线程的名字
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //调用Thread的currentThread()方法获取当前线程
            System.out.println(Thread.currentThread().getName() + ", " + i);
            if (i == 20) {
                //创建并启动第一个线程
                new Usage_16_2_1_CreateThreadByExtendsThread().start();
                //创建并启动第二个线程
                new Usage_16_2_1_CreateThreadByExtendsThread().start();

                /**
                 * //创建、并启动第三个线程
                 * Usage_16_2_1_CreateThreadByExtendsThread extendThread = new Usage_16_2_1_CreateThreadByExtendsThread();
                 *   extendThread.start();
                 *   extendThread.start();//不要对已经处于启动状态的线程再次调用start方法，否则将引发 java.lang.IllegalThreadStateException异常
                 */

            }
        }
    }

    /**
     * 通过继承Thread类来创建并启动多线程的步骤如下：
     *   a.定义Thread类的子类，并重写该类的run()方法，该run()方法的方法体就代表了线程需要完成的任务。因此把run()方法称为线程执行体。
     *   b.创建Thread子类的实例，即创建了线程对象。
     *   c.调用线程对象的start()方法来启动该线程。
     *
     *
     * 当Java程序开始运行后，程序至少会创建一个主线程，主线程的线程执行体不是由run()方法确定的，而是由main()方法确定的——
     * main()方法的方法体代表主线程的线程执行体。
     *
     *
     * 【进行多线程编程不要忘记了Java程序运行时默认的主线程，main()方法的方法体就是主线程的线程执行体】
     *
     *
     * 上面程序还用到了线程的如下两个方法
     *  a> Thread.currentThread(): currentThread()是Thread类的静态方法，该方法总是返回当前正在执行的线程对象。
     *  b> getName()             : 该方法是Thread类的实例方法，该方法返回调用该方法的线程名字。
     *                             (程序可以通过setName(String name)方法为线程设置名字，也可以通过getName()方法返回指定线程的名字。
     *                             默认情况下，主线程的名字为main， 用户启动的多个线程名字依次为Thread-0、Thread-1、Thread-2、、Thread-n等)
     *
     *
     * 【【使用继承Thread类的方式来创建线程类时，多个线程之间无法共享线程类的实例变量。】】
     * 如上述代码看出Thread-0和Thread-1两个线程交替输出时的i变量不连续——注意：i变量是Usage_16_2_1_CreateThreadByExtendsThread的实例变量，
     * 而不是局部变量，故程序每次创建线程对象时都需要创建一个Usage_16_2_1_CreateThreadByExtendsThread实例对象，所以Thread-0和Thread-1
     * 不能共享该实例变量。
     *
     */
}