package com.bamboo.imooc.a100110_synchronized;

/**
 * Synchronized性质--可重入测试(与当前线程有关，与调用者无关)
 *      证明可重入不要求是同一个方法
 *      可重入粒度测试：调用类里另外的同步方法
 * Created by bamboo on 2019-08-08.
 */
public class NatureForReentrant09_Test_02 {

    public synchronized void methodOne() {
        System.out.println("我是 methodOne() 方法");
        methodTwo();
    }

    public synchronized void methodTwo() {
        System.out.println("我是 methodTwo() 方法");
    }

    public static void main(String[] args) {
        NatureForReentrant09_Test_02 test02 = new NatureForReentrant09_Test_02();
        test02.methodOne();
    }

}
