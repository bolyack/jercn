package com.bamboo.imooc.a101100_designpattern.a10_proxy;

/**
 * 使用聚合的方式进行静态代理
 */
public class P012_ProxyByAggregationWay implements Moveable {

    private Car car;

    public P012_ProxyByAggregationWay(Car car) {
        this.car = car;
    }

    public void move() {
        long start = System.currentTimeMillis();
        System.out.println("汽车开始行驶了......");
        car.move();
        long end = System.currentTimeMillis();
        System.out.println("汽车行驶结束了....汽车行驶时间：" + (end - start) + "秒");
    }

    public static void main(String[] args) {
        Car car = new Car();
        P012_ProxyByAggregationWay aggregationWay = new P012_ProxyByAggregationWay(car);
        aggregationWay.move();
    }


}
