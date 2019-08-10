package com.bamboo.core.threads.pools;


public class WorkTask implements Runnable {

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        int r = (int)(Math.random() * 10);
        try {
            Thread.sleep(r * 100);
            long end = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getId() + ",  " + Thread.currentThread().getName() + " is over, cost time ：" + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
