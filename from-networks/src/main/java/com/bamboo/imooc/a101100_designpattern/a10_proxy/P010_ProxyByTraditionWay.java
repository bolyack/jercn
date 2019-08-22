package com.bamboo.imooc.a101100_designpattern.a10_proxy;

import java.util.Random;

/**
 *  传统实现，类似代理功能(在方法执行（开车）前后, 增加时间统计)
 */
public class P010_ProxyByTraditionWay implements Moveable {

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        System.out.println("汽车开始行驶.....");

        try {
            //--假装实现业务逻辑--比如实现开车功能
            Thread.sleep(new Random().nextInt(1000));
            System.out.println("    汽车行驶中.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("汽车行驶结束....汽车行驶时间：" + (end - start) + "毫秒！" );
    }

    public static void main(String[] args) {
        P010_ProxyByTraditionWay proxy = new P010_ProxyByTraditionWay();
        proxy.move();
    }

}
