package com.bamboo.imooc.a100110_synchronized;

/**
 * 反编译字节码
 * > cd D:\git\jercn\from-networks\src\main\java\com\bamboo\imooc\a100110_synchronized>
 * > javac Decompilation10.java
 * > javap -verbose Decompilation10.class
 */
public class Decompilation10 {

    private Object lock = new Object();

    public void saveDo() {
        synchronized(lock) {
            System.out.println(1);
        }
    }

}
