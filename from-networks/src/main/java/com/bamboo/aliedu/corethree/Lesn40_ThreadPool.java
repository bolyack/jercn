package com.bamboo.aliedu.corethree;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 【Java多线程编程-线程池】
 * 线程池具体实现demo
 *   好处：
 *      线程池给开发者带来唯一的好处是允许多个线程按照组的模式进行程序的处理，这样在某一个业务逻辑非常复杂的环境下性能就会得到很好的提升。
 * Created by bamboo on 2017/11/18.
 */
public class Lesn40_ThreadPool {

    /**
     * 创建无大小限制的线程池(根据系统负荷创建无大小限制的线程池，本demo每次执行发现线程池里的对象个数都不一样)
     * newCachedThreadPool
     * @throws Exception
     */
    public static void testNewCachedThreadPool() throws Exception {
        //现在创建了一个线程池的模型，但是里面现在没有线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            int index = i; // 保存x的值
//            Thread.sleep(200); //当线程执行放慢的时候，线程池不需要那么多线程同时执行，一个线程就可以搞定
            //比如超市收银员夜里凌晨只需要一个，但是白天高峰，需要多个。模拟线程池低负荷一个线程即可处理完不需要创建更多的线程对象。
            //打印的10个线程对象都是线程池里的仅有的一个线程。

            //传统写法
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("第" + (index + 1) + "次创建线程" + "; 线程对象名字：" + Thread.currentThread().getName());
//                }
//            }); //执行线程的操作

            executorService.submit(() -> { //lambda表达式
                System.out.println("第" + (index + 1) + "次创建线程" + "; 线程对象名字：" + Thread.currentThread().getName());
            });//执行线程的操作

        }
        executorService.shutdown(); //关闭线程池(代码执行完，线程执行完线程关闭，但线程池不知道, 故需要手动关闭)
    }


    /**
     * 创建单线程池(线程池里每次只有一个线程对象，发现执行的结果打印的顺序也是正常的)
     * newSingleThreadExecutor
     * @throws Exception
     */
    public static void testNewSingleThreadExecutor() throws Exception {
        //现在创建了一个线程池的模型，但是里面现在没有线程
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            int index = i; // 保存x的值

            //传统写法
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("第" + (index + 1) + "次创建线程" + "; 线程对象名字：" + Thread.currentThread().getName());
//                }
//            }); //执行线程的操作

            executorService.submit(() -> { //lambda表达式
                System.out.println("第" + (index + 1) + "次创建线程" + "; 线程对象名字：" + Thread.currentThread().getName());
            });//执行线程的操作

        }
        executorService.shutdown(); //关闭线程池(代码执行完，线程执行完线程关闭，但线程池不知道, 故需要手动关闭)
    }


    /**
     * 创建固定大小的线程池(线程池里每次只有初始固定个数的线程对象, 本demo打印时发现线程对象只有3个)
     * newFixedThreadPool
     * @throws Exception
     */
    public static void testNewFixedThreadPool() throws Exception {
        //现在创建了一个线程池的模型，但是里面现在没有线程
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 50; i++) {
            int index = i; // 保存x的值

            //传统写法
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("第" + (index + 1) + "次创建线程" + "; 线程对象名字：" + Thread.currentThread().getName());
//                }
//            }); //执行线程的操作

            executorService.submit(() -> { //lambda表达式
                System.out.println("第" + (index + 1) + "次创建线程" + "; 线程对象名字：" + Thread.currentThread().getName());
            });//执行线程的操作

        }
        executorService.shutdown(); //关闭线程池(代码执行完，线程执行完线程关闭，但线程池不知道, 故需要手动关闭)
    }


    /**
     * 创建定时任务调度线程池(线程池一直开启等待任务调度执行)
     * newFixedThreadPool
     * @throws Exception
     */
    public static void testNewScheduledThreadPool() throws Exception {
        //现在创建了一个具备有1个线程大小容量的定时任务调度线程池
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println(sdf.format(new Date()) + ", 线程对象名字：" + Thread.currentThread().getName() + "\n");

        for (int i = 0; i < 5; i++) {
            int index = i; // 保存x的值

            //传统写法
//            executorService.scheduleAtFixedRate(new Runnable() {
//                @Override
//                public void run() {
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//                    System.out.println(sdf.format(new Date()) + ", 第" + (index + 1) + "次创建线程" + "; 线程对象名字：" + Thread.currentThread().getName());
//                }
//            }, 3, 5, TimeUnit.SECONDS); //使用一个秒单位，表示3秒后开始执行，而后每5秒执行一次。

            executorService.scheduleAtFixedRate(() -> { //lambda表达式
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
                System.out.println(sdf.format(new Date()) + ", 第" + (index + 1) + "次创建线程" + "; 线程对象名字：" + Thread.currentThread().getName());
            },3, 5, TimeUnit.SECONDS);//执行线程的操作

        }

        //【注意】 关闭线程池(不能关闭，否则调度线程池没得完)
    }

    public static void main(String[] args) throws Exception {
//        testNewCachedThreadPool();
//        testNewSingleThreadExecutor();
        testNewFixedThreadPool();
//        testNewScheduledThreadPool();
    }

}
