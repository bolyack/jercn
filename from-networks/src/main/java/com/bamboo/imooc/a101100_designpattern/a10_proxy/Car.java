package com.bamboo.imooc.a101100_designpattern.a10_proxy;

import java.util.Random;

public class Car implements Moveable {

    public void move() {
        try {
            //--假装实现业务逻辑--比如实现开车功能
            Thread.sleep(new Random().nextInt(1000));
            System.out.println("    汽车行驶中.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
