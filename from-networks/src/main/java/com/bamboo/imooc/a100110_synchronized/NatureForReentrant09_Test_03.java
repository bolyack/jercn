package com.bamboo.imooc.a100110_synchronized;

/**
 * Synchronized性质--可重入测试(与当前线程有关，与调用者无关)
 *      证明可重入不要求是同一个类中的
 *      可重入粒度测试：调用父类的方法
 * Created by bamboo on 2019-08-08.
 */
public class NatureForReentrant09_Test_03 {

    public synchronized void doSomething() {
        System.out.println("我是父类 doSomething() 方法");
    }

}

class NatureForReentrant09_Test_03_Sub extends NatureForReentrant09_Test_03 {

    public synchronized void doSomething() {
        System.out.println("我是子类 doSomething() 方法");
        super.doSomething();
    }

    public static void main(String[] args) {
        NatureForReentrant09_Test_03_Sub sub = new NatureForReentrant09_Test_03_Sub();
        sub.doSomething();
    }

}


//------------------------------------------------------------------------------------------------


class NatureForReentrant09_Test_03_Other_Super {

    public synchronized void otherMethod() {
        System.out.println("我是其他Super类的 otherMethod() 方法");
        NatureForReentrant09_Test_03_Other.otherMethod();
    }

    public static void main(String[] args) {
        NatureForReentrant09_Test_03_Other_Super sp = new NatureForReentrant09_Test_03_Other_Super();
        sp.otherMethod();
    }

}

class NatureForReentrant09_Test_03_Other {

    public static synchronized void otherMethod() {
        System.out.println("我是其他类的 otherMethod() 方法");
    }


}