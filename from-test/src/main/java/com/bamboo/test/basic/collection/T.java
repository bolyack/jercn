package com.bamboo.test.basic.collection;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class T implements Serializable {

    private static class MyThread extends Thread{

        private String name;

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name+" is running in "+Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        MyThread m1=new MyThread("m1");
        MyThread m2=new MyThread("m2");

        m1.start();
        m2.run();
    }

}
