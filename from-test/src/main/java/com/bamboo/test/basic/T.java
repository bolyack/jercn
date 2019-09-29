package com.bamboo.test.basic;

public class T {
    public static void main(String[] args) {

        //Foo的实例对象如何表示
        Foo foo1 = new Foo();//foo1就表示出来了
        //Foo这个类，也是一个实例对象，Class类的实例对象，如何表示呢、
        //任何一个类都是Class的实例对象，这个实例对象那个有三个表示方式
        //第一种表示方式--》实际在告诉我们任何一个类都有一个隐含的静态成员变量class
        Class class1 = Foo.class;

        //第二种表示方式  已经知道该类的对象通过getClass方法
        Class class2 = foo1.getClass();

        /*
         * 官网class1 ,class2表示了Foo类的类类型(class type)
         * 万事万物 都是对象
         * 类也是对象，是Class类的实例对象
         * 这个对象我们称为该类的类类型
         */
        //不管class1  or class2都代表了Foo类的类类型，一个类只可能是Class；类的一个实例对象
        System.out.println(class1 == class2);//true'

        //第三种表达方式
        Class class3 = null;
        try {
            class3 = Class.forName("com.imooc.reflect.Foo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //
        System.out.println(class2 == class3);//true

        //我们完全尅通过类的类类型创建该类的对象实例--》通过class1  or class2 or class3
        //创建Foo类的实例对象
        try {
            //需要有无参数的构造方法
            Foo foo = (Foo) class1.newInstance();//需要强转
            foo.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//
class Foo {
    public void print() {
        System.out.println("foo");
    }
}