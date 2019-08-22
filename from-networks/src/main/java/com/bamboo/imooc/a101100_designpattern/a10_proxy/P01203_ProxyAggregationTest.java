package com.bamboo.imooc.a101100_designpattern.a10_proxy;

/**
 * 聚合方式的测试
 *     使用聚合方式的相对灵活
 */
public class P01203_ProxyAggregationTest {

    public static void main(String[] args) {
        Moveable car = new Car();

        //--使用聚合对功能叠加，先记录日志，后记录时间( 日志在外层)
        P01201_ProxyAggregationTimeCar timeCar = new P01201_ProxyAggregationTimeCar(car);
        P01202_ProxyAggregationLogCar logCar = new P01202_ProxyAggregationLogCar(timeCar);
        logCar.move();

        System.out.println("\n\n");

        //--使用聚合对功能叠加， 先记录时间，后记录日志（记录时间在外层）---调整代理顺序即可
        P01202_ProxyAggregationLogCar logCar1 = new P01202_ProxyAggregationLogCar(car);
        P01201_ProxyAggregationTimeCar timeCar1 = new P01201_ProxyAggregationTimeCar(logCar1);
        timeCar1.move();
    }

}
