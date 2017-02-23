package com.bamboo.bbs.ifeve.java_1_io;

/**
 * Created by admin on 2017/2/14.
 * @see http://ifeve.com/java-io/
 */
public class Usage_01_Summary {

    /**
     *  Java IO教程
     *      Java IO 是一套Java用来读写数据（输入和输出）的API。大部分程序都要处理一些输入，并由输入产生一些输出。Java为此提供了java.io包。
     *      如果你浏览下java.io包，会对其中各样的类选择感到迷惑。这些类的作用都是什么？对于某个任务该选择哪个类？怎样创建你自己的类做插件？
     *      本包目录下文件目的就是给你介绍这些类是如何组织的，以及怎样使用他们，因此你就不会疑惑需要时怎样选取合适的类，或者是否有一个满足你需求的类已经存在了。
     *
     *  Java.io 包的范围
     *      java.io 包并没有涵盖所有输入输出类型。例如，并不包含GUI或者网页上的输入输出，这些输入和输出在其它地方都涉及，比如Swing工程中的JFC (Java Foundation Classes) 类，或者J2EE里的Servlet和HTTP包。
     *      Java.io 包主要涉及文件，网络数据流，内存缓冲等的输入输出。
     */

    /**
     * Java IO(java.io)包下所有类的概述。更具体地说，根据类的用途对类进行分组。这个分组将会使你在未来的工作中，进行类的用途判定时，或者是为某个特定用途选择类时变得更加容易。
     * 【输入和输出 – 数据源和目标媒介】
     *      术语“输入”和“输出”有时候会有一点让人疑惑。一个应用程序的输入往往是另外一个应用程序的输出。那么OutputStream流到底是一个输出到目的地的流呢，还是一个产生输出的流？
     *      InputStream流到底会不会输出它的数据给读取数据的程序呢？就我个人而言，在第一天学习Java IO的时候我就感觉到了一丝疑惑。（校对注：输入流可以理解为向内存输入，输出流可以理解为从内存输出）
     *
     * Java的IO包主要关注的是从原始数据源的读取以及输出原始数据到目标媒介。以下是最典型的数据源和目标媒介：
     *      a.文件
     *      b.管道
     *      c.网络连接
     *      d.内存缓存
     *      e.System.in, System.out, System.error(注：Java标准输入、输出、错误输出)
     *
     * 下面这流程描绘了一个程序从数据源读取数据，然后将数据输出到其他媒介的原理：
     *      Source -> Program -> Destination
     *
     * 【流】
     *      在Java IO中，流是一个核心的概念。流从概念上来说是一个连续的数据流。你既可以从流中读取数据，也可以往流中写数据。
     *      流与数据源(数据流向)的媒介相关联。在Java IO中流既可以是字节流(以字节为单位进行读写)，也可以是字符流(以字符为单位进行读写)。
     *
     * 【类InputStream, OutputStream, Reader 和Writer】
     *      一个程序需要InputStream或者Reader从数据源读取数据，需要OutputStream或者Writer将数据写入到目标媒介中。以下的图例说明了这一点：
     *          Source -> InputStream(Reader) -> Program
     *          Program -> OutputStream(Writer) -> Destination
     *
     *      InputStream和Reader与数据源相关联，OutputStream和writer与目标媒介相关联。
     */

}
