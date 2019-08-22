package com.bamboo.imooc.a101100_designpattern.a10_proxy;

public class P01202_ProxyAggregationLogCar implements Moveable {

    private Moveable moveable;

    public P01202_ProxyAggregationLogCar(Moveable moveable) {
        this.moveable = moveable;
    }

    @Override
    public void move() {
        System.out.println(">>>日志开始....");
        moveable.move();
        System.out.println(">>>日志结束....");
    }

}
