package com.bamboo.core.threads.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  SingleThreadExecutor的含义不仅仅是说只有一个线程的线程池，它能保证任何时候都只有一个任务被执行，且能保证多个提交的任务按照提交的先后顺序被执行
 *      特点：单个核心线程； 禁止重新配置(修改)线程池； 自定义线程池的关闭
 *
 *      a.SingleThreadExecutor就像线程数为1的FixedThreadPool。
 *      b.如果向SingleThreadExecutor提交多个任务，这些任务将排队。从输出结果可以看到，任务按照提交顺序被执行。
 *      c.SingleThreadExecutor会序列化所有提交的任务，并维护自己（隐藏）的悬挂任务队列。？？？（不懂）
 *      d.SingleThreadExecutor可以确保任何线程中都只有唯一的任务在运行。（多个线程使用同一文件系统时，可以用SingleThreadExecutor来保持同步）
 *
 * 那么问题来了，newSingleThreadExecutor()和newFixedThreadPool(1)有什么区别呢？
 *   上面说了那么多，答案应该也很显而易见了，实际上差别仅仅在于newSingleThreadExecutor()返回的线程池
 *   少暴露了一些方法并且多了个finalize方法保证线程池调用shutdown()，仅此而已。

 * @See https://blog.csdn.net/qq_35580883/article/details/78740807
 * @See https://blog.csdn.net/dutsoft/article/details/38947053
 * @See http://www.hiwzc.com/post/4caa2c56.html
 */
public class Test_012_NewSingleThreadExecutor {

    public static void testMain() {
        ExecutorService execs = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 20; i++) {
            execs.execute(new WorkTask());
        }
        execs.shutdown();
    }

    public static void testErrShutdown() {
        ExecutorService execs = Executors.newFixedThreadPool(1);
        execs.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("www.hiwzc.com");
            }
        });
        System.out.println(execs);

        // 能够强制转换成ThreadPoolExecutor并重新配置
        ThreadPoolExecutor executor = (ThreadPoolExecutor) execs;
        executor.setCorePoolSize(2);
        //---因为核心线程修改为2，需要提交一个新任务后才会创建线程，故线程池里核心线程数量增加1
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("www.hiwzc.com");
            }
        });
        System.out.println(executor);

        // 无法关闭线程并结束程序
        execs = null;
        System.gc();
    }

    public static void testSingleShutdown() {
        ExecutorService execs = Executors.newSingleThreadExecutor();
        execs.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("www.hiwzc.com");
            }
        });

        // 能够强制转换成ThreadPoolExecutor并重新配置, 报异常，不能强制转换
        ThreadPoolExecutor executor = (ThreadPoolExecutor) execs;
        executor.setCorePoolSize(2);

        // 自动关闭线程，程序结束;  因为newSingleThreadExecutor->FinalizableDelegatedExecutorService类中，通过实现finalize方法，调用线程的shutdown方法关闭线程，
        //  这样便可以在线程池不被引用时自动被关闭。
        execs = null;
        System.gc();
    }

    public static void main(String[] args) {
//        testMain()
//        testErrShutdown();
        testSingleShutdown();
    }

}
