package com.bamboo.imooc.a100110_synchronized;

/**
 * Synchronized性质--可重入测试(与当前线程有关，与调用者无关)
 *      证明同一个方法是可重入的
 *      可重入粒度测试：递归(模拟)调用本方法
 * Created by bamboo on 2019-08-08.
 */
public class NatureForReentrant09_Test_01 {

    int a = 0;

    private synchronized void methodSelf() {
        System.out.println("这里是 methodSelf() 方法, a = " + a);
        if (a == 0) {  // 这里只模拟2次
            a++;
            methodSelf();
        }
    }

    public static void main(String[] args) {
        NatureForReentrant09_Test_01 test01 = new NatureForReentrant09_Test_01();
        test01.methodSelf();
    }



}
