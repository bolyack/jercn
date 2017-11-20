package com.bamboo.aliedu.corethree;

/**
 * Lambda表达式
 * Created by bamboo on 2017/11/20.
 */
public class Lesn22_Lambda {

    /**
     * Lambda是从JDK1.8开始推出重要新特性。因为再不推出它就晚了，很多的开发语言都开始支持函数式编程，其中最具有代表性的就是haskell。
     * 函数式编程和面向对象的编程可以理解为两大开发阵营，也就是说很多人觉得面向对象的概念过于完整，结构操作过于明确.
     */


    /**
     * 传统的面向对象编程
     *   使用匿名内部类来实现接口最大的好处是节约了一个文件，但是最大的缺点是看的晕了。于是对于此类操作有了更简化实现，
     *   如果采用的是函数式编程模型
     * 缺点：
     *     面向对象编程语法最大的局限要求在于：结构必须非常完整。
     */
    public static void demoOOM() {
        //因该接口的方法只需要实现一次，故这里使用匿名内部类操作而不使用具体的实现类
        IMessage msg = new IMessage() {
            @Override
            public void print() { //必须编写完整语法
                System.out.println("Hello World!");
            }
        };
        msg.print();
    }

    /**
     * 使用函数式编程(Lambda编程)
     * 如果想使用函数式编程有一个前提：
     *    接口必须只有一个方法。如果有两个方法，那么将无法使用函数式编程。
     *    所以如果现在某一个接口就是为了函数式而生的，最好定义的时候就让其只能够定义一个方法，
     *    所以有了一个新的注解叫 @FunctionalInterface
     *
     * 实际上对于以上的语法形式：
     *  1)  (参数) ->  单行语句；
     *
     *  方法本身只包含一行语句， 那么直接编写语句即可，如果要是有多行语句则需要使用：“{}”了。
     */
    public static void demoLambda() {
        //函数式编程的使用，目的还是输出一句话
        IMessage msg = () -> System.out.println("Hello World!");
        msg.print();
    }

    /**
     * lambda方法本身包含多行语句
     */
    public static void mutlLambda() {
        //函数式编程的使用，目的还是输出一句话
        IMessage msg = () -> {
           System.out.println("Hello World!");
            System.out.println("aaaa!");
            System.out.println("bbbb World!");
        };
        msg.print();
    }

    /**
     * 直接进行计算
     * 如果您的表达式里面的内容只是有一行进行数据的返回。那么就直接使用语句即可。
     * 可以不写return
     */
    public static void singleCalcu() {
        IMath math = (p1, p2) -> p1 + p2;
        System.out.println(math.add(10,20));
    }

    public static void main(String[] args) {
//        demoOOM();
//        demoLambda();
//        mutlLambda();
        singleCalcu();
    }

}

@FunctionalInterface  //是一个函数式编程接口， 只允许有一个方法
interface IMessage {
    void print(); //这是一个接口，接口中的抽象方法必须有子类
//    void fun();
}

@FunctionalInterface
interface IMath {
    int add(int x, int y);
}
