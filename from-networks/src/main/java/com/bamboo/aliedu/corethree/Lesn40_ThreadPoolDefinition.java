package com.bamboo.aliedu.corethree;

/**
 * 【Java多线程编程-线程池】
 * 线程池概览(介绍)
 * Created by bamboo on 2017/11/18.
 */
public class Lesn40_ThreadPoolDefinition {

    /**
     * 线程池指的就是多个线程封装在一起进行操作。
     *
     * 在生活中经常遇见这样的场景，例如：现在于经理说了，兄弟们，有个活，三天完成，要求20个人一起干。相当于这
     * 20个人就是20个线程，这些线程捆绑在一起执行。
     *    但是在实际的环境下也有可能出现这几种情况:
     *      - 这个活很大，有多少人我要多少人，一直到完成；
     *      - 这个活很大，但是就要求只能招聘10个人；
     *      - 这个活虽然很大，但是就允许一个人做；
     *
     *
     * 从JDK1.5 之后追加了一个并发访问的程序包: java.util.concurrent, 对于线程池的操作的核心类和接口就定义在此包中。
     * 这里面有个两个核心的【接口】：
     *   - 普通的执行线程池定义：java.util.concurrent.ExecutorService
     *   - 调度线程池： java.util.concurrent.ScheduledExecutorService
     * 如果要进行线程池的创建，一般可以使用java.util.concurrent.Executors【类】完成，有如下几个方法：
     *      # 创建无大小限制的线程池：public static ExecutorService newCachedThreadPool()
     *      # 创建固定大小的线程池： public static ExecutorService newFixedThreadPool(int nThreads)
     *      # 单线程池：public static ExecutorService newSingleThreadExecutor()
     *      # 创建定时任务调度线程池：public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize)
     */

}
