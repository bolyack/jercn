package com.bamboo.ebooks.crazyjavas.ch16_thread;

/**
 * Created by bamboo on 2017/2/22.
 */
public class Usage_16_2_2_CreateTheadByImplInterface {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                System.out.println("==========当j=20时开启两个线程==========");
                Usage_16_2_2_CreateByImplRunnable ut = new Usage_16_2_2_CreateByImplRunnable();

                new Thread(ut).start();
                new Thread(ut, "新线程1").start();
            }
        }
    }

}

class Usage_16_2_2_CreateByImplRunnable implements Runnable {

    private int i = 0;

    public void run() {
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " => " + i);
        }
    }
}
