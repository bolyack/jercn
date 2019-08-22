package com.bamboo.imooc.a101100_designpattern.a10_proxy;

/**
 * 使用继承的方式实现静态代理(不推荐使用)
 *     如果实现代理类的功能叠加和逻辑顺序组合, 需要创建多个类
 */
public class P011_ProxyByExtendWay extends Car {

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        System.out.println("汽车开始行驶....");
        super.move();
        long end = System.currentTimeMillis();
        System.out.println("汽车行驶结束....汽车行驶时间" + (end - start) + "秒");
    }

    public static void main(String[] args) {
//        P11_ProxyByExtendWay proxyExtends = new P11_ProxyByExtendWay();
        Moveable proxyExtends = new P011_ProxyByExtendWay();
        proxyExtends.move();
    }

}
