package com.bamboo.imooc.a101100_designpattern.a10_proxy;

/**
 * 聚合行驶静态代理--代理记录车的行驶时间
 */
public class P01201_ProxyAggregationTimeCar implements Moveable {

    private Moveable moveable;

    public  P01201_ProxyAggregationTimeCar(Moveable moveable) {
        this.moveable = moveable;
    }


    @Override
    public void move() {
        long start = System.currentTimeMillis();
        System.out.println("汽车开始行驶了.....");
        moveable.move();
        long end = System.currentTimeMillis();
        System.out.println("汽车行驶结束了....汽车行驶时间：" + (end - start) + "秒");
    }

}
