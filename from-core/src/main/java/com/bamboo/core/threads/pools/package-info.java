package com.bamboo.core.threads.pools;
/**
 https://jodjod.blog.csdn.net/article/details/88749564
 线程池的分类
 所有实现了ExecutorService接口(Executor的子接口)的实现类都是线程池，可以分为三大类
    a. ForkJoinPool
    b. ScheduledThreadPoolExecutor
    c. ThreadPoolExecutor
  

 具体的线程池，在工具类Executors中预创建了六小类:
 实现了ThreadPoolExecutor类：
    ExecutorService newCachedThreadPool()：无界线程池
    ExecutorService newFixedThreadPool()：有界线程池
    ExecutorService newSingleThreadExecutor()：单一线程池

 实现了ScheduledThreadPoolExecutor类：
    ScheduledExecutorService newSingleThreadScheduledExecutor() 
    ScheduledExecutorService newScheduledThreadPool(int corePoolSize)

 实现了ForkJoinPool类： 
    ExecutorService newWorkStealingPool()  

 !!!! 当然我们也可以自定义线程池 !!!!

 */