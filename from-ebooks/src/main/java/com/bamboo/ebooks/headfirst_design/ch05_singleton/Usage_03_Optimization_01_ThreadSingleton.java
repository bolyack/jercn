package com.bamboo.ebooks.headfirst_design.ch05_singleton;

/**
 * Created by bamboo on 2017/2/20.
 *
 * @see http://www.importnew.com/23491.html
 *
 * 优化单例多线程问题——资源消耗不聚集无影响，放弃优化
 *
 */
public class Usage_03_Optimization_01_ThreadSingleton {

    /**
     * 接上篇 Usage_02_ThreadSingleton
     *【缺点发现】
     * 如果getInstance()的性能对应用程序不是很关键，就什么都别做，没错，如果你的应用程序可以接受getInstance()造成的额外负担，就忘了这件事吧。
     * 同步getInstance()的方法既简单又有效。但是你必须知道，同步一个方法可能造成程序效率下降100倍，因此如果将getInstance()的程序使用在频繁运行的地方，你可能就要重新考虑了。
     */
}
