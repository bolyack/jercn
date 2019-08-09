package com.bamboo.core.threads.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * Executors.newFixedThreadPool(X)创建一个定长X线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 *    创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程。超过这个数目的线程加进去以后，不会运行。
 *    其次，加入线程池的线程属于托管状态，线程的运行不受加入顺序的影响。
 *
 *    主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
 *
 * @See https://www.jianshu.com/p/90796fe95a0b
 * @See https://blog.csdn.net/qq_26286487/article/details/82253475
 * @See https://blog.csdn.net/My_Way666/article/details/83987671
 * @See https://segmentfault.com/a/1190000014107960
 * @See https://www.cnblogs.com/frankyou/p/9057039.html
 * @See https://blog.csdn.net/qq_36299025/article/details/89490636
 * @See https://www.yiibai.com/java_concurrency/concurrency_newfixedthreadpool.html
 * @See https://www.imooc.com/article/23728?block_id=tuijian_wz
 */
public class Test_011_NewFixedThreadPool {

    public static void testMain() {
        ExecutorService execs = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 20; i++) {
            execs.execute(new WorkTask());
        }
        execs.shutdown();
    }

    public static void testException() {
        ExecutorService execs = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            execs.execute(new Runnable() {
                @Override
                public void run() {
                    if (index == 1) {
                        throw new IllegalStateException("handler exception");//线程运行时抛出异常
                    }
                    System.out.println(Thread.currentThread().getName() + "执行");
                    try {
                        Thread.sleep(3000);//模拟当前线程执行其他操作 花费3s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //原文链接：https://blog.csdn.net/qq_35580883/article/details/78740807
                }
            });
        }
        execs.shutdown();
        /**
         * 通过结果看到：可以看到对于newFixedThreadPool(1)，当一个线程在运行时抛出异常也会有其他线程顶替他去完成接下来的任务。
         pool-1-thread-1执行
         Exception in thread "pool-1-thread-1" pool-1-thread-2执行
         java.lang.IllegalStateException: handler exception
         at com.bamboo.core.threads.pools.Test_011_NewFixedThreadPool$1.run(Test_011_NewFixedThreadPool.java:41)
         at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
         at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
         at java.lang.Thread.run(Thread.java:748)
         pool-1-thread-2执行
         pool-1-thread-2执行
         pool-1-thread-2执行
         pool-1-thread-2执行
         pool-1-thread-2执行
         pool-1-thread-2执行
         pool-1-thread-2执行
         */
    }

    public static void main(String[] args) {
//        testMain();
        testException();
    }

    /**
     FixedThreadPool：因为核心线程数是传入且固定的，所以称为有界线程池，一般在后台执行一些辅助性的任务，
                      最大线程数与核心线程数相等；假设核心线程数为4，一次执行20个任务：先启动4个线程，剩下17个任务会进入BlockingQueue排队；
                      因为核心线程数数=最大线程数，所以keepAliveTime这个参数是没有意义的。
     原文链接：https://jodjod.blog.csdn.net/article/details/88749564

     newFixedThreadPool(10)会产生10个线程去读取同一个任务队列,但这10个线程不是同时产生,而是提交一个任务(即执行一次execute()或者submit()方法)产生一个,
     当提交的任务数量超过10个,第11个任务直接提交到blockQueue<Runnable>队列里,然后由这10个线程中的某个线程去获取并执行该任务.
     FixedThreadPool产生的10个线程以后也不会被回收成9个,更不可能增加到11个.

     13,  pool-1-thread-3 is over, cost time ：100
     13,  pool-1-thread-3 is over, cost time ：100
     11,  pool-1-thread-1 is over, cost time ：300
     13,  pool-1-thread-3 is over, cost time ：300
     14,  pool-1-thread-4 is over, cost time ：501
     14,  pool-1-thread-4 is over, cost time ：100
     14,  pool-1-thread-4 is over, cost time ：0
     14,  pool-1-thread-4 is over, cost time ：108
     12,  pool-1-thread-2 is over, cost time ：800
     11,  pool-1-thread-1 is over, cost time ：500
     11,  pool-1-thread-1 is over, cost time ：200
     13,  pool-1-thread-3 is over, cost time ：800
     12,  pool-1-thread-2 is over, cost time ：600
     11,  pool-1-thread-1 is over, cost time ：400
     13,  pool-1-thread-3 is over, cost time ：100
     11,  pool-1-thread-1 is over, cost time ：0
     13,  pool-1-thread-3 is over, cost time ：0
     14,  pool-1-thread-4 is over, cost time ：800
     11,  pool-1-thread-1 is over, cost time ：500
     12,  pool-1-thread-2 is over, cost time ：600

     */

}
