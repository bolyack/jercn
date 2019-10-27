package com.bamboo.test.basic;

import java.util.concurrent.CyclicBarrier;
import java.util.function.Predicate;

/**
 * https://www.cnblogs.com/lvjianwei/p/10578845.html
 * 编写一个程序，开启 3 个线程 A,B,C，这三个线程的输出分别为 A、B、C，每个线程将自己的 输出在屏幕上打印 10 遍，要求输出的结果必须按顺序显示。如：ABCABCABC....
 */
public class Test_1000109_Thread_ABCPrint {

    private static int index = 0;
    private static int max = 15;
    private static Object lock = new Object();

    public static void main(String[] args) {
        Thread a = getThread(i -> i % 3 == 0, "A");
        Thread b = getThread(i -> i % 3 == 1, "B");
        Thread c = getThread(i -> i % 3 == 2, "C");
        a.start();
        b.start();
        c.start();
    }

    private static Thread getThread(Predicate<Integer> condition, String value) {
        return new Thread(() -> {
            while(true) {
                synchronized (lock) {
                    while(!condition.test(index)) {
                        //如果已经不需要继续，直接return,避免继续等待
                        if (index >= max) {
                            return;
                        }
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //如果已经不需要继续，通知所有wait的线程收拾东西回家后，然后自己回家
                    if (index >= max) {
                        lock.notifyAll();
                        return;
                    }
                    System.out.printf("index:%s,value:%s\n", index, value);
                    index++;
                    lock.notifyAll();
                }
            }
        });
    }

}
