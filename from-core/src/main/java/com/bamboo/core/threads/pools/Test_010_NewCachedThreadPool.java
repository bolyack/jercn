package com.bamboo.core.threads.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * newCachedThreadPool()
 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 *
 *  主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
 *
 * 缓存线程池可以提高程序性能，因为长时间保持空闲的这种类型的线程池不会占用任何资源，
 * 调用缓存线程池对象将重用以前构造的线程（线程可用状态），若线程没有可用的，则创建一个新线程添加到池中，
 * 缓存线程池将终止并从池中移除60秒未被使用的线程。
 *
 * 无界线程池，最多可创建Integer.MAX_VALUE个线程，运行结果没有重复的线程号
 *    a.工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger.MAX_VALUE), 这样可灵活的往线程池中添加线程。
 *    b.如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。
 *    c.在使用CachedThreadPool时，一定要注意控制任务的数量，否则，由于大量线程同时运行，很有会造成系统瘫痪。
 */
public class Test_010_NewCachedThreadPool {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            executor.execute(new WorkTask());
        }
        executor.shutdown();
    }

    /**
     *  结果可以看出，线程并没创建出20个，而是复用了已经创建的线程去执行任务

     18,  pool-1-thread-6 is over, cost time ：1
     15,  pool-1-thread-3 is over, cost time ：2
     28,  pool-1-thread-16 is over, cost time ：0       //---随机的时间为0---，故线程立即执行完毕，耗时也就为0
     25,  pool-1-thread-13 is over, cost time ：100
     22,  pool-1-thread-10 is over, cost time ：101
     15,  pool-1-thread-3 is over, cost time ：100     //---和上面相同---，说明线程复用
     13,  pool-1-thread-1 is over, cost time ：401
     16,  pool-1-thread-4 is over, cost time ：402
     27,  pool-1-thread-15 is over, cost time ：500
     26,  pool-1-thread-14 is over, cost time ：500
     23,  pool-1-thread-11 is over, cost time ：600
     31,  pool-1-thread-19 is over, cost time ：600
     29,  pool-1-thread-17 is over, cost time ：600
     20,  pool-1-thread-8 is over, cost time ：700
     14,  pool-1-thread-2 is over, cost time ：703
     19,  pool-1-thread-7 is over, cost time ：805
     24,  pool-1-thread-12 is over, cost time ：804
     17,  pool-1-thread-5 is over, cost time ：900
     21,  pool-1-thread-9 is over, cost time ：900
     30,  pool-1-thread-18 is over, cost time ：900
     * */


    /**
     * 【newCachedThreadPool使用过程中遇到的问题】
     *  在写代码的时候，遇到一个问题，大约有300多个任务。刚开始使用的线程池为newCachedThreadPool，并设置最大阻塞时长60s。
     *   运行时发现，每次都要等到阻塞时间到达后被强制停止，才会执行之后的程序。后来改为newFixedThreadPool(10)来执行，就运行正常了。
     *   感觉很奇怪。通过日志打印，看到当使用newCachedThreadPool线程池的时候，会大约创建300多个线程，随着任务的增加，线程数也会增加，
     *   导致系统资源被大量占用，有的线程被阻塞住了，到设置到的最大阻塞时长时，任务被强制停止。
     *   因此，使用newCachedThreadPool的时候要根据实际任务运行时间情况，因为它可以创建最多Integer.MAX_VALUE个线程，
     *   反而会占用系统资源，降低运行效率。这就是为什么官方文档中会说：newCachedThreadPool会大幅度提高大量短暂异步任务的性能了
     * 原文链接：https://blog.csdn.net/manbuyuzhongAAA/article/details/53573458
     */

}
